package View;

import Model.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class DeleteStudent {
    DeleteStudent(DataBase dataBase, Display display, TableWithStudents myMainTable){
        Shell deleteStudentDialog = new Shell(display, SWT.DIALOG_TRIM);
        deleteStudentDialog.setText("Удаление студента");

        RowLayout rowVertLayout = new RowLayout(SWT.VERTICAL);
        RowLayout rowHorLayout = new RowLayout(SWT.HORIZONTAL);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.verticalSpacing = 100;
        gridLayout.makeColumnsEqualWidth = true;
        deleteStudentDialog.setLayout(rowHorLayout);

        MessageBox searchErrorMessage = new MessageBox(deleteStudentDialog, SWT.APPLICATION_MODAL);
        searchErrorMessage.setMessage("Поиск не дал результатов");

        MessageBox deletionDoneMessage = new MessageBox(deleteStudentDialog, SWT.APPLICATION_MODAL);

        Composite searchEnterSurnameForFirst = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterSurnameForFirstLabel = new Label(searchEnterSurnameForFirst, SWT.NONE);
        Text searchEnterSurnameForFirstText = createTextField(searchEnterSurnameForFirst, searchEnterSurnameForFirstLabel, "фамилию", deleteStudentDialog, gridLayout);

        Composite searchEnterHouse = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterHouseLabel = new Label(searchEnterHouse, SWT.NONE);
        Text searchEnterHouseText = createTextField(searchEnterHouse, searchEnterHouseLabel, "номер дома", deleteStudentDialog, gridLayout);

        Button searchBtnOne = new Button(deleteStudentDialog, SWT.PUSH);
        searchBtnOne.setText("Удалить");

        searchBtnOne.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int colOfFoundStudents = 0;
                for (int criteriaOneSearchIndex = 0; criteriaOneSearchIndex < dataBase.studentList.size(); criteriaOneSearchIndex++) {
                    if (searchEnterSurnameForFirstText.getText().equals(dataBase.getStudent(criteriaOneSearchIndex).getSurName()) || searchEnterHouseText.getText().equals(dataBase.getAddress(criteriaOneSearchIndex).getHouse())) {
                        dataBase.deleteStudent(criteriaOneSearchIndex);
                        dataBase.deleteAddress(criteriaOneSearchIndex);
                        colOfFoundStudents++;
                    }
                }
                if (colOfFoundStudents == 0) {
                    searchErrorMessage.open();
                }
                else {
                    myMainTable.updateTable(myMainTable.getMainTable(), dataBase);
                    deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                    deletionDoneMessage.open();
                }
            }
        });

        Composite searchEnterStreet = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterStreetLabel = new Label(searchEnterStreet, SWT.NONE);
        Text searchEnterStreetText = createTextField(searchEnterStreet, searchEnterStreetLabel, "улицу", deleteStudentDialog, gridLayout);

        Composite searchEnterFlat = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterFlatLabel = new Label(searchEnterFlat, SWT.NONE);
        Text searchEnterFlatText = createTextField(searchEnterFlat, searchEnterFlatLabel, "квартиру", deleteStudentDialog, gridLayout);

        Button searchBtnTwo = new Button(deleteStudentDialog, SWT.PUSH);
        searchBtnTwo.setText("Удалить");
        searchBtnTwo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int colOfFoundStudents = 0;
                for (int criteriaTwoSearchIndex = 0; criteriaTwoSearchIndex < dataBase.studentList.size(); criteriaTwoSearchIndex++) {
                    if (searchEnterStreetText.getText().equals(dataBase.getAddress(criteriaTwoSearchIndex).getStreet()) || searchEnterFlatText.getText().equals(dataBase.getAddress(criteriaTwoSearchIndex).getFlat())) {
                        dataBase.deleteStudent(criteriaTwoSearchIndex);
                        dataBase.deleteAddress(criteriaTwoSearchIndex);
                        colOfFoundStudents++;
                    }
                }
                if (colOfFoundStudents == 0) {
                    searchErrorMessage.open();
                }
                else {
                    myMainTable.updateTable(myMainTable.getMainTable(), dataBase);
                    deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                    deletionDoneMessage.open();
                }
            }
        });

        Composite searchEnterSurnameForThree = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterSurnameForThreeLabel = new Label(searchEnterSurnameForThree, SWT.NONE);
        Text searchEnterSurnameForThreeText = createTextField(searchEnterSurnameForThree, searchEnterSurnameForThreeLabel, "фамилию", deleteStudentDialog, gridLayout);

        Composite searchEnterHouseNum = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterHouseNumLabel = new Label(searchEnterHouseNum, SWT.NONE);
        Text searchEnterHouseNumText = createTextField(searchEnterHouseNum, searchEnterHouseNumLabel, "цифры номеры дома", deleteStudentDialog, gridLayout);

        Button searchBtnThree = new Button(deleteStudentDialog, SWT.PUSH);
        searchBtnThree.setText("Удалить");

        /*Composite chooseCriteria = new Composite(deleteStudentDialog, SWT.NONE);
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
                        for (int criteriaOneSearchIndex = 0; criteriaOneSearchIndex < dataBase.studentList.size(); criteriaOneSearchIndex++) {
                            if (searchEnterSurnameForFirstText.getText().equals(dataBase.getStudent(criteriaOneSearchIndex).getSurName()) || searchEnterHouseText.getText().equals(dataBase.getAddress(criteriaOneSearchIndex).getHouse())) {
                                dataBase.deleteStudent(criteriaOneSearchIndex);
                                dataBase.deleteAddress(criteriaOneSearchIndex);
                                colOfFoundStudents++;
                            }
                        }
                        if (colOfFoundStudents == 0) {
                            searchErrorMessage.open();
                        }
                        else {
                            myMainTable.updateTable(myMainTable.getMainTable(), dataBase);
                            deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
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
                        for (int criteriaTwoSearchIndex = 0; criteriaTwoSearchIndex < dataBase.studentList.size(); criteriaTwoSearchIndex++) {
                            if (searchEnterStreetText.getText().equals(dataBase.getAddress(criteriaTwoSearchIndex).getStreet()) || searchEnterFlatText.getText().equals(dataBase.getAddress(criteriaTwoSearchIndex).getFlat())) {
                                dataBase.deleteStudent(criteriaTwoSearchIndex);
                                dataBase.deleteAddress(criteriaTwoSearchIndex);
                                colOfFoundStudents++;
                            }
                        }
                        if (colOfFoundStudents == 0) {
                            searchErrorMessage.open();
                        }
                        else {
                            myMainTable.updateTable(myMainTable.getMainTable(), dataBase);
                            deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                            deletionDoneMessage.open();
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
                searchEnterSurnameForThree.setLayout(rowHorLayout);

                Label searchEnterHouseNumLabel = new Label(searchEnterHouseNum, SWT.NONE);
                searchEnterHouseNumLabel.setText("Введите цифры номеры дома:");

                Text searchEnterHouseNumText = createTextField(searchEnterHouseNum, searchEnterHouseNumLabel, "цифры номеры дома", searchCriteriaThree, rowHorLayout);

                Button searchBtnThree = new Button(searchCriteriaThree, SWT.PUSH);
                searchBtnThree.setText("Поиск");

                searchCriteriaThree.setSize(350 , 300 );
                searchCriteriaThree.open();
            }
        });*/

        deleteStudentDialog.setSize(950 , 500 );
        deleteStudentDialog.open();
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
