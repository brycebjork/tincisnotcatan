package edu.brown.cs.board;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BoardTest {

  @Test
  public void InitializationTest() {
    Board b = new Board();
    
    assertTrue(b.get_tiles().size() == 19);
    assertTrue(b.get_intersections().size() == 54);
    assertTrue(b.get_paths().size() == 72);
  }

  @Test
  public void TileTest() {
    Board b = new Board();

    Collection<ResourceTile> tiles = b.get_tiles();
    Map<IntersectionCoordinate, Intersection> ints = new HashMap<IntersectionCoordinate, Intersection>();
    Map<PathCoordinate, Path> paths = new HashMap<PathCoordinate, Path>();

    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 0, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(1, 0, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 1, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 0, 1), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(2, 0, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 2, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 0, 2), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(1, 1, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(1, 0, 1), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 1, 1), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(2, 2, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 2, 2), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(2, 0, 2), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 1, 2), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(1, 0, 2), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(2, 0, 1), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(0, 2, 1), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(2, 1, 0), ints,
        paths, TileType.DESERT)));
    assertTrue(tiles.contains(new ResourceTile(0, new HexCoordinate(1, 2, 0), ints,
        paths, TileType.DESERT)));
  }

  @Test
  public void IntersectionTest() {
    Board b = new Board();

    Map<IntersectionCoordinate, Intersection> intersections = b
        .get_intersections();
    Map<IntersectionCoordinate, Intersection> ints = new HashMap<IntersectionCoordinate, Intersection>();
    Map<PathCoordinate, Path> paths = new HashMap<PathCoordinate, Path>();

    //Random Place on Board
    assertTrue(intersections.containsKey(
        new IntersectionCoordinate(
        new HexCoordinate(0, 0, 2), 
        new HexCoordinate(0, 0, 1),
        new HexCoordinate(1, 0, 2))));
    
    //Edge of Board
    assertTrue(intersections.containsKey(
        new IntersectionCoordinate(
        new HexCoordinate(2, 2, 0), 
        new HexCoordinate(1, 2, 0),
        new HexCoordinate(2, 3, 0))));
    
    //Center of Board
    assertTrue(intersections.containsKey(
        new IntersectionCoordinate(
        new HexCoordinate(0, 0, 0), 
        new HexCoordinate(1, 0, 0),
        new HexCoordinate(1, 1, 0))));
  }

  @Test
  public void PathTest() {
    Board b = new Board();

  }
}
