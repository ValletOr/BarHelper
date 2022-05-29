package ValletOr.view;

import ValletOr.model.DrinksTableModel;
import ValletOr.model.IngredientsTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    //==================================================
    //Панели
    //Главная панель, с таблицей
    private JPanel mainPanel;
    private JPanel mainPanelButtonPanel;
    //Панель с подробнастями о напитке. Управление напитком.
    private JPanel infoPanel;
    private JPanel infoPanelFieldsPanel;
    private JPanel infoPanelContextPanel;
    private JPanel infoPanelButtonPanel;
    private JPanel infoPanelIngButtonPanel;
    //Кнопки
    private JButton addButton;
    private JButton delButton;
    private JButton infButton;
    private JButton srcButton;
    private JButton bckButton;
    private JButton cnfButton;
    private JButton addIngButton;
    private JButton delIngButton;
    //Поля для ввода
    private JTextField nameField;
    private JTextArea instField; //instruction
    private JTextField ingField;
    private JTextField amountField;
    private JScrollPane instScrollPane;
    //Лейблы
    private JLabel idLabel;
    private JLabel idExplLabel;
    private JLabel nameExplLabel;
    private JLabel alcExplLabel;
    private JLabel favExplLabel;
    private JLabel errorLabel;
    //Чекбоксы
    private JCheckBox alcCheckbox;
    private JCheckBox favCheckbox;
    //Таблица
    private DrinksTableModel drinksTableModel;
    private JScrollPane scrollPane;
    private JTable mainTable;
    //Таблица для ингридиентов
    private IngredientsTableModel ingredientsTableModel;
    private JScrollPane ingScrollPane;
    private JTable ingTable;
    //Меню
    private JMenuBar menuBar;
    private JMenuItem more;
    private JMenuItem add;
    private JMenuItem search;
    private JMenuItem save;
    //список
    //private RowSorter<table> rowSorter;
    private JList<String> ingList;
    //==================================================
    public MainFrame(){
        super("Помощник бармена");
        init();
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(1080, 720));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(exitListener);

        menuBar = new JMenuBar();
        menuBar.add(createMenu());
        setJMenuBar(menuBar);
        formMainPanel();

        setVisible(true);
        pack();
    }
    private JMenu createMenu(){
        JMenu menu = new JMenu("Меню");
        more = new JMenuItem("Подробнее о напитке");
        add = new JMenuItem("Добавить напиток");
        search = new JMenuItem("Поиск напитков");
        save = new JMenuItem("Сохранить изменения");
        more.addActionListener(new MyListener());
        add.addActionListener(new MyListener());
        search.addActionListener(new MyListener());
        save.addActionListener(new MyListener());
        menu.add(more);
        menu.add(add);
        menu.add(search);
        menu.addSeparator();
        menu.add(save);
        return menu;
    }
    private void formMainPanel(){
        mainPanelButtonPanel.add(addButton);
        mainPanelButtonPanel.add(infButton);
        mainPanelButtonPanel.add(errorLabel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(mainPanelButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(srcButton, BorderLayout.NORTH);
        add(mainPanel);
    }
    private void addInfoPanel(){
        infoPanelButtonPanel.add(cnfButton);
        infoPanelButtonPanel.add(delButton);
        infoPanelButtonPanel.add(bckButton);
        infoPanelIngButtonPanel.add(errorLabel);
        infoPanelIngButtonPanel.add(ingField);
        infoPanelIngButtonPanel.add(amountField);
        infoPanelIngButtonPanel.add(addIngButton);
        infoPanelIngButtonPanel.add(delIngButton);
        infoPanelContextPanel.add(infoPanelFieldsPanel);
        infoPanelContextPanel.add(ingScrollPane);
        infoPanelContextPanel.add(infoPanelIngButtonPanel);
        infoPanelContextPanel.add(new JScrollPane(ingList));
        infoPanelContextPanel.add(instScrollPane);

        //=====
        infoPanelFieldsPanel.add(idExplLabel);
        infoPanelFieldsPanel.add(idLabel);
        infoPanelFieldsPanel.add(nameExplLabel);
        infoPanelFieldsPanel.add(nameField);
        infoPanelFieldsPanel.add(alcExplLabel);
        infoPanelFieldsPanel.add(alcCheckbox);
        infoPanelFieldsPanel.add(favExplLabel);
        infoPanelFieldsPanel.add(favCheckbox);
        //=====
        infoPanel.add(infoPanelContextPanel, BorderLayout.CENTER);
        infoPanel.add(infoPanelButtonPanel, BorderLayout.SOUTH);
        //=====
        add(infoPanel);
    }
    private void addCreatePanel(){
        infoPanelButtonPanel.add(cnfButton);
        infoPanelButtonPanel.add(bckButton);
        infoPanelIngButtonPanel.add(errorLabel);
        infoPanelIngButtonPanel.add(ingField);
        infoPanelIngButtonPanel.add(amountField);
        infoPanelIngButtonPanel.add(addIngButton);
        infoPanelIngButtonPanel.add(delIngButton);
        infoPanelContextPanel.add(infoPanelFieldsPanel);
        infoPanelContextPanel.add(ingScrollPane);
        infoPanelContextPanel.add(infoPanelIngButtonPanel);
        infoPanelContextPanel.add(new JScrollPane(ingList));
        infoPanelContextPanel.add(instScrollPane);
        //=====
        infoPanelFieldsPanel.add(idExplLabel);
        infoPanelFieldsPanel.add(idLabel);
        infoPanelFieldsPanel.add(nameExplLabel);
        infoPanelFieldsPanel.add(nameField);
        infoPanelFieldsPanel.add(alcExplLabel);
        infoPanelFieldsPanel.add(alcCheckbox);
        infoPanelFieldsPanel.add(favExplLabel);
        infoPanelFieldsPanel.add(favCheckbox);
        //=====
        infoPanel.add(infoPanelContextPanel, BorderLayout.CENTER);
        infoPanel.add(infoPanelButtonPanel, BorderLayout.SOUTH);
        //=====
        add(infoPanel);
    }
    private void addSearchPanel(){
        infoPanelButtonPanel.add(cnfButton);
        infoPanelButtonPanel.add(bckButton);
        infoPanelIngButtonPanel.add(errorLabel);
        infoPanelIngButtonPanel.add(ingField);
        infoPanelIngButtonPanel.add(amountField);
        infoPanelIngButtonPanel.add(addIngButton);
        infoPanelIngButtonPanel.add(delIngButton);
        infoPanelContextPanel.add(infoPanelFieldsPanel);
        infoPanelContextPanel.add(ingScrollPane);
        infoPanelContextPanel.add(infoPanelIngButtonPanel);
        infoPanelContextPanel.add(new JScrollPane(ingList));
        infoPanelContextPanel.add(instScrollPane);
        //=====
        infoPanelFieldsPanel.add(nameExplLabel);
        infoPanelFieldsPanel.add(nameField);
        infoPanelFieldsPanel.add(alcExplLabel);
        infoPanelFieldsPanel.add(alcCheckbox);
        infoPanelFieldsPanel.add(favExplLabel);
        infoPanelFieldsPanel.add(favCheckbox);
        //=====
        infoPanel.add(infoPanelContextPanel, BorderLayout.CENTER);
        infoPanel.add(infoPanelButtonPanel, BorderLayout.SOUTH);
        //=====
        add(infoPanel);
    }
    private void init(){
        drinksTableModel = new DrinksTableModel();
        mainTable = new JTable();
        mainTable.setModel(drinksTableModel);
        mainTable.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(mainTable);
        mainPanel = new JPanel(new BorderLayout());
        mainPanelButtonPanel = new JPanel();
        addButton = new JButton("Добавить напиток");
        infButton = new JButton("Подробнее о напитке");
        srcButton = new JButton("Поиск напитков");
        addButton.addActionListener(new MyListener());
        infButton.addActionListener(new MyListener());
        srcButton.addActionListener(new MyListener());

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
    }
    private void infoInit(int selectedDrink){
        ingredientsTableModel = drinksTableModel.getTableModelForDrink(selectedDrink);
        ingTable = new JTable();
        ingTable.setModel(ingredientsTableModel);
        ingScrollPane = new JScrollPane(ingTable);
        infoPanel = new JPanel(new BorderLayout());
        infoPanelContextPanel = new JPanel();
        infoPanelFieldsPanel = new JPanel(new GridLayout(4,2,5,12));
        infoPanelButtonPanel = new JPanel();
        cnfButton = new JButton("Сохранить");
        delButton = new JButton("Удалить");
        bckButton = new JButton("Отмена");
        cnfButton.addActionListener(new MyListener());
        delButton.addActionListener(new MyListener());
        bckButton.addActionListener(new MyListener());
        idLabel = new JLabel(Integer.toString(drinksTableModel.getID(selectedDrink)));
        idExplLabel = new JLabel("Drink ID: ");
        nameField = new JTextField(drinksTableModel.getName(selectedDrink));
        nameExplLabel = new JLabel("Drink name: ");
        alcCheckbox = new JCheckBox();
        alcCheckbox.setSelected(drinksTableModel.isAlc(selectedDrink));
        alcExplLabel = new JLabel("Alcohol: ");
        favCheckbox = new JCheckBox();
        favCheckbox.setSelected(drinksTableModel.isFav(selectedDrink));
        favExplLabel = new JLabel("Favorite: ");
        instField = new JTextArea(drinksTableModel.getInst(selectedDrink), 5, 80);
        instScrollPane = new JScrollPane(instField);

        infoPanelIngButtonPanel = new JPanel();
        infoPanelIngButtonPanel.setLayout(new GridLayout(6,1,5,5));
        ingField = new JTextField(10);
        amountField = new JTextField(10);
        addIngButton = new JButton("Добавить ингредиент");
        delIngButton = new JButton("Удалить ингредиент");
        addIngButton.addActionListener(new MyListener());
        delIngButton.addActionListener(new MyListener());

        ingList = new JList(createListModel());
        ingList.setVisibleRowCount(5);
        ingList.addListSelectionListener(new MyListListener());
        ingField.getDocument().addDocumentListener(new MyDocmentListener());
    }
    private void createInit(){
        ingredientsTableModel = new IngredientsTableModel();
        ingTable = new JTable();
        ingTable.setModel(ingredientsTableModel);
        ingScrollPane = new JScrollPane(ingTable);
        infoPanel = new JPanel(new BorderLayout());
        infoPanelContextPanel = new JPanel();
        infoPanelFieldsPanel = new JPanel(new GridLayout(4,2,5,12));
        infoPanelButtonPanel = new JPanel();
        cnfButton = new JButton("Добавить");
        bckButton = new JButton("Отмена");
        cnfButton.addActionListener(new MyListener());
        bckButton.addActionListener(new MyListener());
        idLabel = new JLabel(Integer.toString(drinksTableModel.findFreeID()));
        idExplLabel = new JLabel("Drink ID: ");
        nameField = new JTextField("", 10);
        nameExplLabel = new JLabel("Drink name: ");
        alcCheckbox = new JCheckBox();
        alcExplLabel = new JLabel("Alcohol: ");
        favCheckbox = new JCheckBox();
        favExplLabel = new JLabel("Favorite: ");
        instField = new JTextArea("", 5,80);
        instScrollPane = new JScrollPane(instField);

        infoPanelIngButtonPanel = new JPanel();
        infoPanelIngButtonPanel.setLayout(new GridLayout(5,1,5,5));
        ingField = new JTextField(10);
        amountField = new JTextField(10);
        addIngButton = new JButton("Добавить ингредиент");
        delIngButton = new JButton("Удалить ингредиент");
        addIngButton.addActionListener(new MyListener());
        delIngButton.addActionListener(new MyListener());

        ingList = new JList(createListModel());
        ingList.setVisibleRowCount(5);
        ingList.addListSelectionListener(new MyListListener());
        ingField.getDocument().addDocumentListener(new MyDocmentListener());
    }
    private void searchInit(){
        ingredientsTableModel = new IngredientsTableModel();
        ingTable = new JTable();
        ingTable.setModel(ingredientsTableModel);
        ingScrollPane = new JScrollPane(ingTable);
        infoPanel = new JPanel(new BorderLayout());
        infoPanelContextPanel = new JPanel();
        infoPanelFieldsPanel = new JPanel(new GridLayout(3,2,5,12));
        infoPanelButtonPanel = new JPanel();
        cnfButton = new JButton("Поиск");
        bckButton = new JButton("Отмена");
        cnfButton.addActionListener(new MyListener());
        bckButton.addActionListener(new MyListener());
        nameField = new JTextField("", 10);
        nameExplLabel = new JLabel("Drink name: ");
        alcCheckbox = new JCheckBox();
        alcExplLabel = new JLabel("Alcohol: ");
        favCheckbox = new JCheckBox();
        favExplLabel = new JLabel("Favorite: ");
        instField = new JTextArea("", 5,80);
        instScrollPane = new JScrollPane(instField);

        infoPanelIngButtonPanel = new JPanel();
        infoPanelIngButtonPanel.setLayout(new GridLayout(5,1,5,5));
        ingField = new JTextField(10);
        amountField = new JTextField(10);
        addIngButton = new JButton("Добавить ингредиент");
        delIngButton = new JButton("Удалить ингредиент");
        addIngButton.addActionListener(new MyListener());
        delIngButton.addActionListener(new MyListener());

        ingList = new JList(createListModel());
        ingList.setVisibleRowCount(5);
        ingList.addListSelectionListener(new MyListListener());
        ingField.getDocument().addDocumentListener(new MyDocmentListener());
    }

    private void infoPanelCreator(){
        if(mainTable.getSelectedRow() != -1) {
            infoInit(mainTable.getSelectedRow());
            addInfoPanel();
            mainPanelStateChanger();
        }else{
            errorLabel.setText("Напиток не выбран!");
        }
    }
    private void infoPanelRemover(){
        remove(infoPanel);
        mainPanelStateChanger();
    }
    private void infoPanelDrinkSave(){
        drinksTableModel.setDrink(mainTable.getSelectedRow(), nameField.getText(), alcCheckbox.isSelected(), favCheckbox.isSelected(), instField.getText(), ingredientsTableModel.getIngredients(), ingredientsTableModel.getAmounts());
    }
    private void infoPanelDrinkDel(){
        drinksTableModel.delDrink(mainTable.getSelectedRow());
    }
    private void mainPanelStateChanger(){
        mainPanel.setVisible(!mainPanel.isVisible());
        more.setEnabled(!more.isEnabled());
        add.setEnabled(!add.isEnabled());
        search.setEnabled(!search.isEnabled());
        revalidate();
        repaint();
    }
    private void drinkCreator(){
        createInit();
        addCreatePanel();
        mainPanelStateChanger();
    }
    private void drinkAdder(){
        drinksTableModel.addDrink(Integer.parseInt(idLabel.getText()), nameField.getText(), alcCheckbox.isSelected(), favCheckbox.isSelected(), instField.getText(), ingredientsTableModel.getIngredients(), ingredientsTableModel.getAmounts());
    }
    private void ingAdder(){
        try {
            ingredientsTableModel.addIngredient(ingField.getText(), amountField.getText());
            ingField.setBackground(Color.WHITE);
        }catch (Exception e){
            errorLabel.setText("Пустой ингредиент!");
            ingField.setBackground(Color.RED);
        }
        ingTable.repaint();
    }
    private void ingRemover(){
        ingredientsTableModel.delIngredient(ingTable.getSelectedRow()); //Нужен обработчик
        ingTable.repaint();
    }
    private void searchCreator(){
        searchInit();
        addSearchPanel();
        mainPanelStateChanger();
        srcButton.setText("Отменить поиск");
    }
    private void searchDrinks(){
        drinksTableModel.searchByFilter(nameField.getText(), alcCheckbox.isSelected(), favCheckbox.isSelected(), instField.getText(), ingredientsTableModel.getIngredients(), ingredientsTableModel.getAmounts());

    }
    private void searchCancel(){
        drinksTableModel.searchCancel();
        srcButton.setText("Поиск напитков");
    }

    private ListModel<String> createListModel(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String s : drinksTableModel.getIngredientsList()){
            model.addElement(s);
        }
        return model;
    }

    private void filterIngModel(DefaultListModel<String> model, String filter){
        for(String s : drinksTableModel.getIngredientsList()){
            if(!s.startsWith(filter)){
                if (model.contains(s)){
                    model.removeElement(s);
                }
            } else{
                if (!model.contains(s)){
                    model.addElement(s);
                }
            }
        }
    }

    //==================================================
    class MyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            errorLabel.setText("");
            switch (e.getActionCommand()){
                case "Подробнее о напитке":
                    infoPanelCreator();
                    break;
                case "Отмена":
                    infoPanelRemover();
                    break;
                case "Сохранить":
                    infoPanelDrinkSave();
                    infoPanelRemover();
                    break;
                case "Удалить":
                    infoPanelDrinkDel();
                    infoPanelRemover();
                    break;
                case "Добавить напиток":
                    drinkCreator();
                    break;
                case "Добавить":
                    drinkAdder();
                    infoPanelRemover();
                    break;
                case "Добавить ингредиент":
                    ingAdder();
                    break;
                case "Удалить ингредиент":
                    ingRemover();
                    break;
                case "Поиск напитков":
                    searchCreator();
                    break;
                case "Поиск":
                    searchDrinks();
                    infoPanelRemover();
                    break;
                case "Отменить поиск":
                    searchCancel();
                    break;
                case "Сохранить изменения":
                    drinksTableModel.saveDB();
                    break;
            }
        }
    }

    class MyListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ingField.setText(ingList.getSelectedValue().toString());
        }
    }

    class MyDocmentListener implements DocumentListener{
        @Override public void insertUpdate(DocumentEvent e) {
            filterIngList();
        }
        @Override public void removeUpdate(DocumentEvent e) {
            filterIngList();
        }
        @Override public void changedUpdate(DocumentEvent e) {
            filterIngList();
        }
        private void filterIngList(){
            String filter = ingField.getText();
            filterIngModel((DefaultListModel<String>)ingList.getModel(), filter);
        }
    }

    WindowListener exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
                    null, "Сохранить изменения?",
                    "Выход", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                drinksTableModel.saveDB();
                System.exit(0);
            }else{
                System.exit(0);
            }
        }
    };
}
