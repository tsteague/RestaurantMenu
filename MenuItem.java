import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class MenuItem
{
  private String name_ = null;
  private String description_ = null;
  private String category_ = null;
  private double price_ = 0.00;
  private Date menuItemLastModified_ = null;
  private List<String> ingredientAllergens_ = null;

  public MenuItem( String name, String description, double price, Date savedDate )
  {
    name_ = name;
    description_ = description;
    price_ = price;
 
    menuItemLastModified_ = savedDate;
  }

  public MenuItem( String name, String description, double price )
  {
    name_ = name;
    description_ = description;
    price_ = price;

    menuItemLastModified_ = new Date();
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
    if ( !Menu.CATEGORIES.contains(category) )
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
    menuItemLastModified_ = addDate;
  }
  public Date getDateAdded()
  {
    return menuItemLastModified_;
  }
  public long getSaveDate()
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime( getDateAdded() );
    
    return cal.getTimeInMillis();
  }
  public boolean isNew()
  {
    Calendar threeDaysOld = Calendar.getInstance();
    threeDaysOld.add( Calendar.DAY_OF_MONTH, -3 );

    Calendar menuDate = Calendar.getInstance();
    menuDate.setTime( getDateAdded() );

    return menuDate.before( threeDaysOld );
  }
}
