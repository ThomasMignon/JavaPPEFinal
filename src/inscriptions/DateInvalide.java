package inscriptions;

public class DateInvalide extends Exception
{
	public DateInvalide()
    {
        System.out.println("La date est invalide");
    }

    public String toString()
    {
      return "You tried to do an illegal assignement !";
    }
}
