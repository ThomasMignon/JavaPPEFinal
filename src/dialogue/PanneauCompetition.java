package dialogue;
import javax.swing.JPanel;
public class PanneauCompetition extends JPanel 
{
	JPanel panelTableauCompetition = new JPanel();
	JPanel tableCompetitions = new JPanel();
	
	public PanneauCompetition()
	{
		setListener();
		
		setPanelTableCompetitions();
	}
	
	public void setListener()
	{
		
	}
	
	public void setPanelTableCompetitions()
	{
		tableCompetitions.add(setTableCompetitions());
		this.add(tableCompetitions);
	}
	
	public JPanel setTableCompetitions()
	{
		TableCompetition table = new TableCompetition(this);
		panelTableauCompetition.add(table);
		return panelTableauCompetition;
	}
	
}