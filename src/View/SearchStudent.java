package View;

import Model.Address;
import Model.DataBase;
import Model.Student;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;



public class SearchStudent {
    DataBase dataBase;
    Shell searchStudentDialog;
    TableWithStudents myMainTableForSearch;

    SearchStudent(DataBase dataBase, Display display){
        Shell searchStudentDialog = new Shell(display, SWT.DIALOG_TRIM);
        searchStudentDialog.setText("Поиск студента");

        RowLayout rowVertLayout = new RowLayout(SWT.VERTICAL);
        RowLayout rowHorLayout = new RowLayout(SWT.HORIZONTAL);

        searchStudentDialog.setLayout(rowVertLayout);

        DataBase dataBaseForFound = new DataBase();

        MessageBox searchErrorMessage = new MessageBox(searchStudentDialog, SWT.APPLICATION_MODAL);
        searchErrorMessage.setMessage("Поиск не дал результатов");

        myMainTableForSearch = new TableWithStudents(searchStudentDialog, dataBase);

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
                        for (int criteriaOneSearchIndex = 0; criteriaOneSearchIndex < dataBase.studentList.size(); criteriaOneSearchIndex++) {
                            if (searchEnterSurnameForFirstText.getText().equals(dataBase.getStudent(criteriaOneSearchIndex).getSurName()) || searchEnterHouseText.getText().equals(dataBase.getAddress(criteriaOneSearchIndex).getHouse())) {
                                dataBaseForFound.addStudent(dataBase.getStudent(criteriaOneSearchIndex));
                                dataBaseForFound.addAddress(dataBase.getAddress(criteriaOneSearchIndex));
                                //myMainTableForSearch.updateTableWithPaging(10, dataBaseForFound.studentList.size() / 20, myMainTableForSearch.getMainTable(), dataBaseForFound);
                                myMainTableForSearch.updateTable(myMainTableForSearch.getMainTable(), dataBaseForFound);
                            }
                        }
                        if (dataBaseForFound.studentList.size() == 0) {
                            myMainTableForSearch.updateTable(myMainTableForSearch.getMainTable(), dataBaseForFound);
                            searchErrorMessage.open();
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
                        for (int criteriaTwoSearchIndex = 0; criteriaTwoSearchIndex < dataBase.studentList.size(); criteriaTwoSearchIndex++) {
                            if (searchEnterStreetText.getText().equals(dataBase.getAddress(criteriaTwoSearchIndex).getStreet()) || searchEnterFlatText.getText().equals(dataBase.getAddress(criteriaTwoSearchIndex).getFlat())) {
                                dataBaseForFound.addStudent(dataBase.getStudent(criteriaTwoSearchIndex));
                                dataBaseForFound.addAddress(dataBase.getAddress(criteriaTwoSearchIndex));
                                //myMainTableForSearch.updateTableWithPaging(10, dataBaseForFound.studentList.size() / 20, myMainTableForSearch.getMainTable(), dataBaseForFound);
                                myMainTableForSearch.updateTable(myMainTableForSearch.getMainTable(), dataBaseForFound);
                            }
                        }
                        if (dataBaseForFound.studentList.size() == 0) {
                            myMainTableForSearch.updateTable(myMainTableForSearch.getMainTable(), dataBaseForFound);
                            searchErrorMessage.open();
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
                        char[] inputArray = searchEnterHouseNumText.getText().toCharArray();
                        for (int criteriaThreeSearchIndex = 0; criteriaThreeSearchIndex < dataBase.studentList.size(); criteriaThreeSearchIndex++) {
                            if (searchEnterSurnameForThreeText.getText().equals(dataBase.getStudent(criteriaThreeSearchIndex).getSurName()) || searchEnterHouseNumText.getText().equals(dataBase.getAddress(criteriaThreeSearchIndex).getHouse())) {
                                dataBaseForFound.addStudent(dataBase.getStudent(criteriaThreeSearchIndex));
                                dataBaseForFound.addAddress(dataBase.getAddress(criteriaThreeSearchIndex));
                                //myMainTableForSearch.updateTableWithPaging(10, dataBaseForFound.studentList.size() / 20, myMainTableForSearch.getMainTable(), dataBaseForFound);
                                myMainTableForSearch.updateTable(myMainTableForSearch.getMainTable(), dataBaseForFound);
                            }
                        }
                        if (dataBaseForFound.studentList.size() == 0) {
                            myMainTableForSearch.updateTable(myMainTableForSearch.getMainTable(), dataBaseForFound);
                            searchErrorMessage.open();
                        }
                    }
                });

                searchCriteriaThree.setSize(350 , 300 );
                searchCriteriaThree.open();
            }
        });

        searchStudentDialog.setSize(750 , 500 );
        searchStudentDialog.open();
    }

    public Text createTextField(Composite compositeName, Label labelName, String text, Shell parent, Layout layout) {
        compositeName = new Composite(parent, SWT.NONE);
        compositeName.setLayout(layout);

        labelName = new Label(compositeName, SWT.NONE);
        labelName.setText("Введите "+text+":");

        Text textName = new Text(compositeName, SWT.CENTER);
        textName.setSize(100, 100);

        compositeName.setBounds(10, 10, 250, 100);
        return textName;
    }
}
