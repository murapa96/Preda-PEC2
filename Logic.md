classDiagram
direction BT
class BackTrackLogic {
  + solve() String
   Building building
}
class BackTrackTraceLogic {
  + solve() String
   String trace
   Building building
}
class Building {
  + getTile(int, int) Tiles
  + toString() String
  + setTile(int, int, Tiles) void
  + setVisited(int, int, boolean) void
  + isVisited(int, int) boolean
   String map
   Tiles[][] mapTiles
   int columns
   int rows
}
class Controller {
  + getisTraceEnabled() boolean
  + setisTraceEnabled(boolean) void
  + execute(String[]) void
}
class Logic {
<<Interface>>
  + solve() String
}
class Main {
  + main(String[]) void
}
class RouteItem {
  + toString() String
   int y
   int x
   Tiles tile
}
class Tiles {
<<enumeration>>
  + getTile(char) Tiles?
  + getChar(Tiles) char
  + values() Tiles[]
  + valueOf(String) Tiles
}
class View {
  + showResult(String) void
  + readUserInput() String
  + showError(String) void
  + showHelp() void
  + showInfo(String) void
}

BackTrackLogic "1" *--> "building 1" Building 
BackTrackLogic  ..>  Logic 
BackTrackLogic  ..>  RouteItem : «create»
BackTrackTraceLogic "1" *--> "building 1" Building 
BackTrackTraceLogic  ..>  Logic 
BackTrackTraceLogic  ..>  RouteItem : «create»
Building  ..>  Tiles : «create»
Building "1" *--> "map 1" Tiles 
Controller  ..>  BackTrackLogic : «create»
Controller  ..>  BackTrackTraceLogic : «create»
Controller "1" *--> "building 1" Building 
Controller  ..>  Building : «create»
Controller "1" *--> "logic 1" Logic 
Controller  ..>  View : «create»
Controller "1" *--> "view 1" View 
Main  ..>  Controller : «create»
RouteItem "1" *--> "tile 1" Tiles 
