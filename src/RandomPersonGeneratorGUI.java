// import statements
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This class assembles the UI for the program.
 * @author		Richard Barney
 * @version		1.0.0 June 2015
 */
public class RandomPersonGeneratorGUI extends JPanel {
	/** Eclipse-generated serialVersionUID. */
	private static final long serialVersionUID = 5581101208861879989L;
	/** JRadioButton to specify male gender. */
	private JRadioButton maleJRadioButton;
	/** JRadioButton to specify female gender. */
	private JRadioButton femaleJRadioButton;
	/** JTextField to display full name. */
	private JTextField fullNameJTextField;
	/** JTextField to display email address. */
	private JTextField emailAddressJTextField;
	/** JTextField to display birthday. */
	private JTextField birthdayJTextField;
	/** JTextField to display age. */
	private JTextField ageJTextField;
	/** JTextField to display occupation (job). */
	private JTextField occupationJTextField;
	/** JTextField to display birthplace. */
	private JTextField birthplaceJTextField;
	/** JTextField to display phone number. */
	private JTextField phoneJTextField;
	/** JTextField to display height. */
	private JTextField heightJTextField;
	/** JTextField to display weight. */
	private JTextField weightJTextField;
	/** JTextField to display martial status. */
	private JTextField martialStatusJTextField;
	/** JTextField to display blood type. */
	private JTextField bloodTypeJTextField;
	/** JTextField to display eye color. */
	private JTextField eyeColorJTextField;
	/** JTextField to display hair color. */
	private JTextField hairColorJTextField;
	/** JTextField to display maiden name. */
	private JTextField maidenNameJTextField;
	/** JTextField to display mother's maiden name. */
	private JTextField mothersMaidenNameJTextField;
	/** JTextField to display number of children. */
	private JTextField numberOfChildrenJTextField;
	/** JTextField to display favorite color. */
	private JTextField favoriteColorJTextField;
	/** JTextField to display car. */
	private JTextField carJTextField;
	/** JTextField to display favroite food. */
	private JTextField favoriteFoodJTextField;
	/** JButton to generate a new random person. */
	private JButton generatePersonJButton;
	/** RandomPersonGenerator object. */
	private RandomPersonGenerator randomPersonGenerator;
	
	/**
	 * Default constructor.
	 */
	public RandomPersonGeneratorGUI() {
		randomPersonGenerator = new RandomPersonGenerator();
		maleJRadioButton = new JRadioButton("Male", true); // selected by default
		femaleJRadioButton = new JRadioButton("Female");
		fullNameJTextField = new JTextField();
		emailAddressJTextField = new JTextField();
		birthdayJTextField = new JTextField();
		ageJTextField = new JTextField();
		occupationJTextField = new JTextField();
		birthplaceJTextField = new JTextField();
		phoneJTextField = new JTextField();
		heightJTextField = new JTextField();
		weightJTextField = new JTextField();
		martialStatusJTextField = new JTextField();
		bloodTypeJTextField = new JTextField();
		eyeColorJTextField = new JTextField();
		hairColorJTextField = new JTextField();
		maidenNameJTextField = new JTextField();
		mothersMaidenNameJTextField  = new JTextField();
		numberOfChildrenJTextField = new JTextField();
		favoriteColorJTextField = new JTextField();
		carJTextField = new JTextField();
		favoriteFoodJTextField = new JTextField();
		generatePersonJButton = new JButton("Generate");
		configureEvents();
		assembleUserInterface();
	}
	
	/**
	 * Void method that assembles the UI. Makes use of GridLayouts for
	 * the columns and a BorderLayout to put everything together.
	 */
	private void assembleUserInterface() {
		// left side containing JLabels in one column with 19 rows
		JPanel labelsJPanel = new JPanel(new GridLayout(19,1));
		labelsJPanel.add(new JLabel("Full name"));
		labelsJPanel.add(new JLabel("E-mail"));
		labelsJPanel.add(new JLabel("Born"));
		labelsJPanel.add(new JLabel("Age"));
		labelsJPanel.add(new JLabel("Occupation"));
		labelsJPanel.add(new JLabel("Birthplace"));
		labelsJPanel.add(new JLabel("Phone"));
		labelsJPanel.add(new JLabel("Height"));
		labelsJPanel.add(new JLabel("Weight"));
		labelsJPanel.add(new JLabel("Martial status"));
		labelsJPanel.add(new JLabel("Blood type"));
		labelsJPanel.add(new JLabel("Eye color"));
		labelsJPanel.add(new JLabel("Hair color"));
		labelsJPanel.add(new JLabel("Maiden name"));
		labelsJPanel.add(new JLabel("No. of children"));
		labelsJPanel.add(new JLabel("Favorite color"));
		labelsJPanel.add(new JLabel("Car"));
		labelsJPanel.add(new JLabel("Favorite food"));
		labelsJPanel.add(new JLabel("Mother's maiden name"));
		
		// right side containing JTextFields in one column with 19 rows
		JPanel fieldsJPanel = new JPanel(new GridLayout(19,1));
		fieldsJPanel.add(fullNameJTextField);
		fieldsJPanel.add(emailAddressJTextField);
		fieldsJPanel.add(birthdayJTextField);
		fieldsJPanel.add(ageJTextField);
		fieldsJPanel.add(occupationJTextField);
		fieldsJPanel.add(birthplaceJTextField);
		fieldsJPanel.add(phoneJTextField);
		fieldsJPanel.add(heightJTextField);
		fieldsJPanel.add(weightJTextField);
		fieldsJPanel.add(martialStatusJTextField);
		fieldsJPanel.add(bloodTypeJTextField);
		fieldsJPanel.add(eyeColorJTextField);
		fieldsJPanel.add(hairColorJTextField);
		fieldsJPanel.add(maidenNameJTextField);
		fieldsJPanel.add(numberOfChildrenJTextField);
		fieldsJPanel.add(favoriteColorJTextField);
		fieldsJPanel.add(carJTextField);
		fieldsJPanel.add(favoriteFoodJTextField);
		fieldsJPanel.add(mothersMaidenNameJTextField);
		
		// ButtonGroup to hold the two JRadioButtons
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(maleJRadioButton);
		buttonGroup.add(femaleJRadioButton);
		
		// put the two JRadioButtons together
		JPanel radioButtonsJPanel = new JPanel(new GridLayout(1,2));
		radioButtonsJPanel.add(maleJRadioButton, BorderLayout.CENTER);
		radioButtonsJPanel.add(femaleJRadioButton, BorderLayout.WEST);
		
		// put the UI together
		this.setLayout(new BorderLayout());
		this.add(radioButtonsJPanel, BorderLayout.NORTH);
		this.add(labelsJPanel, BorderLayout.WEST);
		this.add(fieldsJPanel, BorderLayout.CENTER);
		this.add(generatePersonJButton, BorderLayout.SOUTH);
	} // end method assembleUserInterface
	
	/**
	 * Void method that adds an ActionListener to the JButton to generate
	 * a new random person. Calls on methods within RandomPersonGenerator.
	 */
	public void configureEvents() {
		generatePersonJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (maleJRadioButton.isSelected()) {
					fullNameJTextField.setText(randomPersonGenerator.generateFullName("male"));
				}
				else {
					fullNameJTextField.setText(randomPersonGenerator.generateFullName("female"));
				}
				emailAddressJTextField.setText(randomPersonGenerator.generateEmail());
				birthdayJTextField.setText(randomPersonGenerator.generateBirthday());
				occupationJTextField.setText(randomPersonGenerator.generateOccupation());
				birthplaceJTextField.setText(randomPersonGenerator.generateBirthplace());
				ageJTextField.setText(randomPersonGenerator.getAge());
				phoneJTextField.setText(randomPersonGenerator.generatePhone());
				heightJTextField.setText(randomPersonGenerator.generateHeight());
				weightJTextField.setText(randomPersonGenerator.generateWeight());
				martialStatusJTextField.setText(randomPersonGenerator.generateMartialStatus());
				bloodTypeJTextField.setText(randomPersonGenerator.generateBloodType());
				eyeColorJTextField.setText(randomPersonGenerator.generateEyeColor());
				hairColorJTextField.setText(randomPersonGenerator.generateHairColor());
				maidenNameJTextField.setText(randomPersonGenerator.generateMaidenName());
				mothersMaidenNameJTextField.setText(randomPersonGenerator.generateMothersMaidenName());
				numberOfChildrenJTextField.setText(randomPersonGenerator.generateNumberOfChildren());
				favoriteColorJTextField.setText(randomPersonGenerator.generateFavoriteColor());
				carJTextField.setText(randomPersonGenerator.generateCar());
				favoriteFoodJTextField.setText(randomPersonGenerator.generateFavoriteFood());
			}
		});
	} // end method configureEvents
} // end class RandomPersonGeneratorGUI