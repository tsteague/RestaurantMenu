import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Menu
{
  private Map<String,MenuItem> menuItems_ = new HashMap<String,MenuItem>(); 
  private Date menuLastModified_ = null;

  private final static String MENU_FILE = "menu";
  public final static List<String> CATEGORIES = initCategories();
  private static Pattern itemPattern_ = Pattern.compile( "\\\"([^\\\"]+)\\\",?" );
  private static Matcher itemMatcher_ = null;

  public Menu()
  {
    populateMenu();
  }

  public void addItem( MenuItem newMenuItem ) throws Exception
  {
    if ( hasMenuItem(newMenuItem.getName()) )
    {
      throw new Exception( newMenuItem.getName() + " already exists." );
    }
    menuItems_.put( newMenuItem.getName().toLowerCase(), newMenuItem );

    storeMenu();
  }
  public void removeItem( MenuItem newMenuItem ) throws Exception
  {
    if ( hasMenuItem(newMenuItem.getName()) )
    {
      menuItems_.remove( newMenuItem.getName().toLowerCase() );

      storeMenu();
    }
  }

  public MenuItem getItem( String name )
  {
    return menuItems_.get( name.trim().toLowerCase() );
  }
  public List<MenuItem> getMenuCategory( String category )
  {
    List<MenuItem> categoryItems = new ArrayList<MenuItem>();
  
    for ( MenuItem item : menuItems_.values() )
    {
      if ( item.getCategory().equalsIgnoreCase(category) )
      {
        categoryItems.add( item );
      }
    }
  
    return categoryItems;
  }

  public boolean hasMenuItem( String newName )
  {
    return menuItems_.containsKey( newName.trim().toLowerCase() );
  }

  public void printMenu()
  {
    for( String category : CATEGORIES )
    {
      System.out.println( "----- " + category + " -----" );
      for ( MenuItem item : menuItems_.values() )
      {
        if ( item.getCategory().equalsIgnoreCase(category) )
        {
          System.out.println( item.getName() );
          System.out.println( item.getDescription() );
          System.out.println( "$" + item.getPrice() );
          System.out.println();
        }
      }
    }
  }

  private boolean populateMenu()
  {
    boolean success = false;

    BufferedReader br = null;
    try
    {
      File menuFile = new File( MENU_FILE );
      if ( menuFile.exists() )
      {
        DateFormat df = DateFormat.getDateInstance();
        MenuItem menuItem = null;
        String name = null;
        String description = null;
        String category = null;
        double price = 0.00;
        Date dt = null;

        br = new BufferedReader( new FileReader(MENU_FILE) );
        
        String line = null;
        while ( (line = br.readLine()) != null )
        {
          itemMatcher_ = itemPattern_.matcher( line );
          int count = 0;
          while( itemMatcher_.find() )
          {
            if ( count == 0 )
            {
              name = itemMatcher_.group(1);
            }
            else if ( count == 1 )
            {
              description = itemMatcher_.group(1);           
            }
            else if ( count == 2 )
            {
              category = itemMatcher_.group(1);           
            }
            else if ( count == 3 )
            {
              price = Double.parseDouble( itemMatcher_.group(1) );
            }
            else if ( count == 4 )
            {
              dt = new Date( Long.parseLong(itemMatcher_.group(1)) );
            }
            count++;
          }
          menuItem = new MenuItem( name, description, price );
          menuItem.setCategory( category );

          addItem( menuItem );
        }
      }
      success = true;
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        br.close();
      }
      catch( Exception ex ) {}
    }
  
    return success;
  }

  private boolean storeMenu()
  {
    boolean success = false;
 
    BufferedWriter bw = null;
    try
    {
      bw = new BufferedWriter( new FileWriter(MENU_FILE) );
      for ( MenuItem item : menuItems_.values() )
      {
        bw.write( "\"" + item.getName() + "\"" );
        bw.write( ",\"" + item.getDescription() + "\"" );
        bw.write( ",\"" + item.getCategory() + "\"" );
        bw.write( ",\"" + item.getPrice() + "\"" );
        bw.write( ",\"" + item.getSaveDate() + "\"" );
        bw.newLine();
      }
      success = true;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        bw.close();
      }
      catch ( Exception ex ) {}
    }

    return success;
  }
    
    
  private static List<String> initCategories()
  {
    List<String> categories = new ArrayList<String>();
    categories.add( "appetizer" );
    categories.add( "main course" );
    categories.add( "dessert" );
    categories.add( "drink" );
    categories.add( "alcohol" );
    categories.add( "kids" );
    categories.add( "special" );

    return categories;
  }
}
