package edu.brown.cs.networking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import org.eclipse.jetty.websocket.api.Session;

import spark.Spark;

// Grand Central Terminal - Routes all of the inputs to appropriate groups
public class GCT {

  private static final GCT                                 instance                =
      new GCT();

  private static final PriorityBlockingQueue<SessionGroup> pending                 =
      new PriorityBlockingQueue<>();

  private static final List<SessionGroup>                  full                    =
      Collections.synchronizedList(new ArrayList<>());

  private static final Map<Session, SessionGroup>          groupMap                =
      new ConcurrentHashMap<>();


  private static final int                                 NEED_TO_GENERALIZE_THIS =
      2;

  private static int                                       GROUP_ID                =
      1;
  private static int                                       SESSION_ID              =
      1;


  public static GCT getInstance() {
    return instance;
  }


  private GCT() {
    Spark.webSocket("/action", ReceivingHandler.class);
    Spark.init();
  }


  public boolean register(Session s) {
    SessionGroup candidate = pending.poll();

    if (candidate == null) {
      candidate =
          new SessionGroup(NEED_TO_GENERALIZE_THIS, String.valueOf(GROUP_ID++));
      candidate.add(s);
      groupMap.put(s, candidate);
    } else if (candidate.isFull()) {
      throw new IllegalStateException("Had a full SessionGroup in pending PQ");
    } else {
      candidate.add(s);
      groupMap.put(s, candidate);
    }

    if (candidate.isFull()) {
      full.add(candidate);
    } else {
      pending.add(candidate);
    }
    return true; // figure out what this boolean should really represent TODO
  }


  public boolean remove(Session s, int statusCode, String reason) {
    SessionGroup group = groupMap.remove(s);

    if (group == null) {
      return false;
    }

    if (group.isFull()) {
      assert full.remove(group) : "This group should have been in full";
    } else {
      assert pending.remove(group) : "This group should have been in pending";
    }

    boolean different = group.remove(s);

    if (different) {
      group.stampNow(); // groups that lose a user move to the back of the line.
    }
    if (!group.isEmpty()) {
      pending.add(group);
    }

    return different;

  }


  public boolean message(Session s, String message) {
    SessionGroup sg = groupMap.get(s);
    if (sg == null) {
      return false;
    }
    return sg.message(s, message);
  }



}