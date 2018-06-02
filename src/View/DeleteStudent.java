package View;

import Controller.StudentController;
import Model.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class DeleteStudent {
    DeleteStudent(Shell shell, StudentController studentController, Display display, TableWithStudents myMainTable){
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
        Text searchEnterSurnameForFirstText = SearchStudent.createTextField(searchEnterSurnameForFirst, searchEnterSurnameForFirstLabel, "фамилию", deleteStudentDialog, gridLayout);

        Composite searchEnterHouse = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterHouseLabel = new Label(searchEnterHouse, SWT.NONE);
        Text searchEnterHouseText = SearchStudent.createTextField(searchEnterHouse, searchEnterHouseLabel, "номер дома", deleteStudentDialog, gridLayout);

        Button searchBtnOne = new Button(deleteStudentDialog, SWT.PUSH);
        searchBtnOne.setText("Удалить");

        searchBtnOne.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int colOfFoundStudents = 0;
                for (int criteriaOneSearchIndex = 0; criteriaOneSearchIndex < studentController.getStudentListSize(); criteriaOneSearchIndex++) {
                    if (searchEnterSurnameForFirstText.getText().equals(studentController.getStudentFromDataBase(criteriaOneSearchIndex).getSurName()) || Integer.parseInt(searchEnterHouseText.getText()) == (studentController.getAddressFromDataBase(criteriaOneSearchIndex).getHouse())) {
                        studentController.deleteStudentFromDataBase(criteriaOneSearchIndex);
                        studentController.deleteAddressFromDataBase(criteriaOneSearchIndex);
                        colOfFoundStudents++;
                    }
                }
                if (colOfFoundStudents == 0) {
                    searchErrorMessage.open();
                }
                else {
                    myMainTable.updateTable(shell, myMainTable.getMainTable(), studentController);
                    deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                    deletionDoneMessage.open();
                }
            }
        });

        Composite searchEnterStreet = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterStreetLabel = new Label(searchEnterStreet, SWT.NONE);
        Text searchEnterStreetText = SearchStudent.createTextField(searchEnterStreet, searchEnterStreetLabel, "улицу", deleteStudentDialog, gridLayout);

        Composite searchEnterFlat = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterFlatLabel = new Label(searchEnterFlat, SWT.NONE);
        Text searchEnterFlatText = SearchStudent.createTextField(searchEnterFlat, searchEnterFlatLabel, "квартиру", deleteStudentDialog, gridLayout);

        Button searchBtnTwo = new Button(deleteStudentDialog, SWT.PUSH);
        searchBtnTwo.setText("Удалить");
        searchBtnTwo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int colOfFoundStudents = 0;
                for (int criteriaTwoSearchIndex = 0; criteriaTwoSearchIndex < studentController.getStudentListSize(); criteriaTwoSearchIndex++) {
                    if (searchEnterStreetText.getText().equals(studentController.getAddressFromDataBase(criteriaTwoSearchIndex).getStreet()) || Integer.parseInt(searchEnterFlatText.getText()) == (studentController.getAddressFromDataBase(criteriaTwoSearchIndex).getFlat())) {
                        studentController.deleteStudentFromDataBase(criteriaTwoSearchIndex);
                        studentController.deleteAddressFromDataBase(criteriaTwoSearchIndex);
                        colOfFoundStudents++;
                    }
                }
                if (colOfFoundStudents == 0) {
                    searchErrorMessage.open();
                }
                else {
                    myMainTable.updateTable(shell, myMainTable.getMainTable(), studentController);
                    deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                    deletionDoneMessage.open();
                }
            }
        });

        Composite searchEnterSurnameForThree = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterSurnameForThreeLabel = new Label(searchEnterSurnameForThree, SWT.NONE);
        Text searchEnterSurnameForThreeText = SearchStudent.createTextField(searchEnterSurnameForThree, searchEnterSurnameForThreeLabel, "фамилию", deleteStudentDialog, gridLayout);

        Composite searchEnterHouseNum = new Composite(deleteStudentDialog, SWT.NONE);
        Label searchEnterHouseNumLabel = new Label(searchEnterHouseNum, SWT.NONE);
        Text searchEnterHouseNumText = SearchStudent.createTextField(searchEnterHouseNum, searchEnterHouseNumLabel, "цифры номеры дома", deleteStudentDialog, gridLayout);

        Button searchBtnThree = new Button(deleteStudentDialog, SWT.PUSH);
        searchBtnThree.setText("Удалить");

        searchBtnThree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int colOfFoundStudents = 0;
                char[] inputArray = searchEnterHouseNumText.getText().toCharArray();
                for (int criteriaThreeSearchIndex = 0; criteriaThreeSearchIndex < studentController.getStudentListSize(); criteriaThreeSearchIndex++) {
                    char[] dataBaseAddresArray = String.valueOf(studentController.getAddressFromDataBase(criteriaThreeSearchIndex).getHouse()).toCharArray();
                    dataBaseAddresArray = SearchStudent.deleteDupls(dataBaseAddresArray);
                    for (char aDataBaseAddresArray : dataBaseAddresArray) {
                        if (searchEnterSurnameForThreeText.getText().equals(studentController.getStudentFromDataBase(criteriaThreeSearchIndex).getSurName()) && inputArray[0] == aDataBaseAddresArray) {
                            studentController.deleteStudentFromDataBase(criteriaThreeSearchIndex);
                            studentController.deleteAddressFromDataBase(criteriaThreeSearchIndex);
                            colOfFoundStudents++;
                        }
                    }
                }
                if (colOfFoundStudents == 0) {
                    searchErrorMessage.open();
                }
                else {
                    myMainTable.updateTable(shell, myMainTable.getMainTable(), studentController);
                    deletionDoneMessage.setMessage("Было удалено " + colOfFoundStudents + " студентов.");
                    deletionDoneMessage.open();
                }
            }
        });

        deleteStudentDialog.setSize(950 , 500 );
        deleteStudentDialog.open();
    }
}
