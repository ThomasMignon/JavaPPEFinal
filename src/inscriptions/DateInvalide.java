package inscriptions;

public class DateInvalide extends Exception
{
	private static final long serialVersionUID = -2829648070101565348L;

	public DateInvalide()
    {
        System.out.println("La date est invalide");
    }

    public String toString()
    {
      return "You tried to do an illegal assignement !";
    }
}
