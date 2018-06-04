package View;

import Controller.StudentController;
import Model.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class SearchStudent {
    private Shell searchStudentDialog;
    private TableWithStudents myMainTableForSearch;

    SearchStudent(StudentController studentController, Display display){
        searchStudentDialog = new Shell(display, SWT.DIALOG_TRIM);
        searchStudentDialog.setText("Поиск студента");

        RowLayout rowVertLayout = new RowLayout(SWT.VERTICAL);
        RowLayout rowHorLayout = new RowLayout(SWT.HORIZONTAL);

        searchStudentDialog.setLayout(rowVertLayout);

        StudentController studentControllerForFound = new StudentController(new DataBase());

        MessageBox searchErrorMessage = new MessageBox(searchStudentDialog, SWT.APPLICATION_MODAL);
        searchErrorMessage.setMessage("Поиск не дал результатов");

        MessageBox deletionDoneMessage = new MessageBox(searchStudentDialog, SWT.APPLICATION_MODAL);

        myMainTableForSearch = new TableWithStudents();
        myMainTableForSearch.createTable(searchStudentDialog, studentControllerForFound);
        myMainTableForSearch.createPaging(searchStudentDialog, studentControllerForFound);

        Composite chooseCriteria = new Composite(searchStudentDialog, SWT.NONE);
        chooseCriteria.setLayout(rowVertLayout);

        Button criteriaOne = new Button(chooseCriteria, SWT.PUSH);
        criteriaOne.setText("По номеру дома и фамилии");
        criteriaOne.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                Shell searchCriteriaOne = new Shell(display, SWT.DIALOG_TRIM);
                searchCriteriaOne.setLayout(rowVertLayout);

                Composite searchEnterSurnameForFirst = new Composite(searchCriteriaOne, SWT.NONE);
                Label searchEnterSurnameForFirstLabel = new Label(searchEnterSurnameForFirst, SWT.NONE);
                Text searchEnterSurnameForFirstText = createTextField(searchEnterSurnameForFirst, searchEnterSurnameForFirstLabel, "фамилию", searchCriteriaOne, rowHorLayout);

                Composite searchEnterHouse = new Composite(searchCriteriaOne, SWT.NONE);
                Label searchEnterHouseLabel = new Label(searchEnterHouse, SWT.NONE);
                Text searchEnterHouseText = createTextField(searchEnterHouse, searchEnterHouseLabel, "номер дома", searchCriteriaOne, rowHorLayout);

                Button searchBtnOne = new Button(searchCriteriaOne, SWT.PUSH);
                searchBtnOne.setText("Поиск");

                searchBtnOne.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        int colOfFoundStudents = 0;
                        for (int criteriaOneSearchIndex = 0; criteriaOneSearchIndex < studentController.getStudentListSize(); criteriaOneSearchIndex++) {
                            if (searchEnterSurnameForFirstText.getText().equals(studentController.getStudentFromDataBase(criteriaOneSearchIndex).getSurName()) && Integer.parseInt(searchEnterHouseText.getText()) == (studentController.getAddressFromDataBase(criteriaOneSearchIndex).getHouse())) {
                                studentControllerForFound.addStudentToDataBase(studentController.getStudentFromDataBase(criteriaOneSearchIndex));
                                studentControllerForFound.addAddressToDataBase(studentController.getAddressFromDataBase(criteriaOneSearchIndex));
                                colOfFoundStudents++;
                            }
                        }
                        if (studentControllerForFound.getStudentListSize() == 0) {
                            myMainTableForSearch.updateTable(searchStudentDialog, myMainTableForSearch.getMainTable(), studentControllerForFound);
                            searchErrorMessage.open();
                        }
                        else if (studentControllerForFound.getStudentListSize() <= myMainTableForSearch.getOnPage()){
                            deletionDoneMessage.setMessage("Было найдено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
                            myMainTableForSearch.updateTable(searchStudentDialog, myMainTableForSearch.getMainTable(), studentControllerForFound);
                        }
                        else if (studentControllerForFound.getStudentListSize() > myMainTableForSearch.getOnPage()) {
                            deletionDoneMessage.setMessage("Было найдено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
                            myMainTableForSearch.updateTableWithPaging(searchStudentDialog, myMainTableForSearch.getOnPage(), myMainTableForSearch.getCurrentPage(), myMainTableForSearch.getMainTable(), studentControllerForFound);
                        }
                    }
                });

                searchCriteriaOne.setSize(350 , 300 );
                searchCriteriaOne.open();
            }
        });

        Button criteriaTwo = new Button(chooseCriteria, SWT.PUSH);
        criteriaTwo.setText("По улице и квартире");
        criteriaTwo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                Shell searchCriteriaTwo = new Shell(display, SWT.DIALOG_TRIM);
                searchCriteriaTwo.setLayout(rowVertLayout);

                Composite searchEnterStreet = new Composite(searchCriteriaTwo, SWT.NONE);
                Label searchEnterStreetLabel = new Label(searchEnterStreet, SWT.NONE);
                Text searchEnterStreetText = createTextField(searchEnterStreet, searchEnterStreetLabel, "улицу", searchCriteriaTwo, rowHorLayout);

                Composite searchEnterFlat = new Composite(searchCriteriaTwo, SWT.NONE);
                Label searchEnterFlatLabel = new Label(searchEnterFlat, SWT.NONE);
                Text searchEnterFlatText = createTextField(searchEnterFlat, searchEnterFlatLabel, "квартиру", searchCriteriaTwo, rowHorLayout);

                Button searchBtnTwo = new Button(searchCriteriaTwo, SWT.PUSH);
                searchBtnTwo.setText("Поиск");
                searchBtnTwo.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        int colOfFoundStudents = 0;
                        for (int criteriaTwoSearchIndex = 0; criteriaTwoSearchIndex < studentController.getStudentListSize(); criteriaTwoSearchIndex++) {
                            if (searchEnterStreetText.getText().equals(studentController.getAddressFromDataBase(criteriaTwoSearchIndex).getStreet()) && Integer.parseInt(searchEnterFlatText.getText()) == (studentController.getAddressFromDataBase(criteriaTwoSearchIndex).getFlat())) {
                                studentControllerForFound.addStudentToDataBase(studentController.getStudentFromDataBase(criteriaTwoSearchIndex));
                                studentControllerForFound.addAddressToDataBase(studentController.getAddressFromDataBase(criteriaTwoSearchIndex));
                                colOfFoundStudents++;
                            }
                        }
                        if (studentControllerForFound.getStudentListSize() == 0) {
                            myMainTableForSearch.updateTable(searchStudentDialog, myMainTableForSearch.getMainTable(), studentControllerForFound);
                            searchErrorMessage.open();
                        }
                        else if (studentControllerForFound.getStudentListSize() <= myMainTableForSearch.getOnPage()){
                            deletionDoneMessage.setMessage("Было найдено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
                            myMainTableForSearch.updateTable(searchStudentDialog, myMainTableForSearch.getMainTable(), studentControllerForFound);
                        }
                        else if (studentControllerForFound.getStudentListSize() > myMainTableForSearch.getOnPage()) {
                            deletionDoneMessage.setMessage("Было найдено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
                            myMainTableForSearch.updateTableWithPaging(searchStudentDialog, myMainTableForSearch.getOnPage(), myMainTableForSearch.getCurrentPage(), myMainTableForSearch.getMainTable(), studentControllerForFound);
                        }
                    }
                });

                searchCriteriaTwo.setSize(350 , 300 );
                searchCriteriaTwo.open();
            }
        });

        Button criteriaThree = new Button(chooseCriteria, SWT.PUSH);
        criteriaThree.setText("По фамилии и цифрам, встречающимся в номере дома");
        criteriaThree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                Shell searchCriteriaThree = new Shell(display, SWT.DIALOG_TRIM);
                searchCriteriaThree.setLayout(rowVertLayout);

                Composite searchEnterSurnameForThree = new Composite(searchCriteriaThree, SWT.NONE);
                Label searchEnterSurnameForThreeLabel = new Label(searchEnterSurnameForThree, SWT.NONE);
                Text searchEnterSurnameForThreeText = createTextField(searchEnterSurnameForThree, searchEnterSurnameForThreeLabel, "фамилию", searchCriteriaThree, rowHorLayout);

                Composite searchEnterHouseNum = new Composite(searchCriteriaThree, SWT.NONE);
                Label searchEnterHouseNumLabel = new Label(searchEnterHouseNum, SWT.NONE);
                Text searchEnterHouseNumText = createTextField(searchEnterHouseNum, searchEnterHouseNumLabel, "цифры номеры дома", searchCriteriaThree, rowHorLayout);

                Button searchBtnThree = new Button(searchCriteriaThree, SWT.PUSH);
                searchBtnThree.setText("Поиск");
                searchBtnThree.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        int colOfFoundStudents = 0;
                        char[] inputArray = searchEnterHouseNumText.getText().toCharArray();
                        for (int criteriaThreeSearchIndex = 0; criteriaThreeSearchIndex < studentController.getStudentListSize(); criteriaThreeSearchIndex++) {
                            char[] dataBaseAddresArray = String.valueOf(studentController.getAddressFromDataBase(criteriaThreeSearchIndex).getHouse()).toCharArray();
                            dataBaseAddresArray = deleteDupls(dataBaseAddresArray);
                            for (char aDataBaseAddresArray : dataBaseAddresArray) {
                                if (searchEnterSurnameForThreeText.getText().equals(studentController.getStudentFromDataBase(criteriaThreeSearchIndex).getSurName()) && inputArray[0] == aDataBaseAddresArray) {
                                    studentControllerForFound.addStudentToDataBase(studentController.getStudentFromDataBase(criteriaThreeSearchIndex));
                                    studentControllerForFound.addAddressToDataBase(studentController.getAddressFromDataBase(criteriaThreeSearchIndex));
                                    colOfFoundStudents++;
                                }
                            }
                        }
                        if (studentControllerForFound.getStudentListSize() == 0) {
                            myMainTableForSearch.updateTable(searchStudentDialog, myMainTableForSearch.getMainTable(), studentControllerForFound);
                            searchErrorMessage.open();
                        }
                        else if (studentControllerForFound.getStudentListSize() <= myMainTableForSearch.getOnPage()){
                            deletionDoneMessage.setMessage("Было найдено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
                            myMainTableForSearch.updateTable(searchStudentDialog, myMainTableForSearch.getMainTable(), studentControllerForFound);
                        }
                        else if (studentControllerForFound.getStudentListSize() > myMainTableForSearch.getOnPage()) {
                            deletionDoneMessage.setMessage("Было найдено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
                            myMainTableForSearch.updateTableWithPaging(searchStudentDialog, myMainTableForSearch.getOnPage(), myMainTableForSearch.getCurrentPage(), myMainTableForSearch.getMainTable(), studentControllerForFound);
                        }
                    }
                });

                searchCriteriaThree.setSize(350 , 300 );
                searchCriteriaThree.open();
            }
        });

        searchStudentDialog.setSize(1350 , 900 );
        searchStudentDialog.open();
    }

    public static Text createTextField(Composite compositeName, Label labelName, String text, Shell parent, Layout layout) {
        compositeName = new Composite(parent, SWT.NONE);
        compositeName.setLayout(layout);

        labelName = new Label(compositeName, SWT.NONE);
        labelName.setText("Введите "+text+":");

        Text textName = new Text(compositeName, SWT.CENTER);
        textName.setSize(100, 100);

        compositeName.setBounds(10, 10, 250, 100);
        return textName;
    }

    public static char[] deleteDupls(char[] inputArray) {
        int n = inputArray.length;

        for ( int i = 0, m = 0; i != n; i++, n = m ) {
            for ( int j = m = i + 1; j != n; j++ ) {
                if ( inputArray[j] != inputArray[i] ) {
                    if ( m != j ) inputArray[m] = inputArray[j];
                    m++;
                }
            }
        }
        if ( n != inputArray.length ) {
            char[] b = new char[n];
            for ( int i = 0; i < n; i++ ) b[i] = inputArray[i];

            inputArray = b;
        }
        return inputArray;
    }
}
