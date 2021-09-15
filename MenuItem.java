import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class MenuItem
{
  private String name_ = null;
  private String description_ = null;
  private String category_ = null;
  private List<String> ingredientAllergens_ = null;
  private double price_ = 0.00;

  private Date menuLastModified_ = null;
  private static List<String> CATEGORIES = initCategories();

  public MenuItem( String name, String description, double price )
  {
    name_ = name;
    description_ = description;
    price_ = price;

    menuLastModified_ = new Date();
  }

  public void setName( String name )
  {
    name_ = name;
  }
  public String getName()
  {
    return name_;
  }

  public void setDescription( String description )
  {
    description_ = description;
  }
  public String getDescription()
  {
    return description_;
  }

  public void setCategory( String category ) throws Exception
  {
    if ( !CATEGORIES.contains(category) )
    {
      throw new Exception( "Not a menu category" );
    }
    category_ = category;
  }
  public String getCategory()
  {
    return category_;
  }

  public void setPrice( double price )
  {
    price_ = price;
  }
  public double getPrice()
  {
    return price_;
  }

  public void setDateAdded( Date addDate )
  {
    menuLastModified_ = addDate;
  }
  public Date getDateAdded()
  {
    return menuLastModified_;
  }
  public boolean isNew()
  {
    Calendar weekOld = Calendar.getInstance();
    weekOld.add( Calendar.DAY_OF_MONTH, -7 );

    Calendar menuDate = Calendar.getInstance();
    menuDate.setTime( getDateAdded() );

    return menuDate.before( weekOld );
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
