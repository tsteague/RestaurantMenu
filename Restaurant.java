import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class Restaurant
{
  public static void main( String[] args )
  {
    if ( args.length == 0 )
    {
      System.out.println( "Use --help for usage" );
      System.exit( 1 );
    }

    if ( args[0].trim().equalsIgnoreCase("--help") )
    {
      System.out.print( "Usage: java Menu [options]\n"
        + "  --addItem <name> <description> <category> <price>...\n"
        + "            Add a new menu item with required fields\n"
        + "  --removeItem <name>\n"
        + "            Delete a menu item\n"
        + "  --print <item name|category>\n"
        + "            Print the entire menu or print the optional\n"
        + "            provided item name\n"
        + "  --shownew Show new menu items added\n" );
    }

    if ( args.length == 5 && args[0].trim().equalsIgnoreCase("--addItem") )
    {
      System.out.println( "Adding " + args[1] + "|" + args[2] + "|" + args[4] + "|" + args[3] );
      MenuItem newItem = new MenuItem( args[1].trim(), args[2].trim(), Double.parseDouble(args[4].trim()) );
      try
      {
        newItem.setCategory( args[3].toLowerCase().trim() );

        Menu menu = new Menu();
        menu.addItem( newItem );
      }
      catch ( Exception e )
      {
        e.printStackTrace();
      }
    }
    else if ( args.length == 1 && args[0].trim().equalsIgnoreCase("--print") )
    {
      Menu menu = new Menu();
      menu.printMenu();
    }
    else if ( args.length == 2 && args[0].trim().equalsIgnoreCase("--print") && 
              args[1].trim().equalsIgnoreCase("categories") )
    {
      Menu menu = new Menu();
      menu.printCategories();
    }
    else if ( args.length == 2 && args[0].trim().equalsIgnoreCase("--print") )
    {
      Menu menu = new Menu();
      menu.printMenu( args[1].trim() );
    }
    else if ( args.length == 2 && args[0].trim().equalsIgnoreCase("--removeItem") )
    {
      Menu menu = new Menu();
      try
      {
        menu.removeItem( args[1].trim() );
      }
      catch ( Exception e )
      {
        System.out.println( "Error removing " + args[1] + ": " + e.getMessage() );
      }
    }
  }
}
