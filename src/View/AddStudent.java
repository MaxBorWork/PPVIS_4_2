package View;

import Controller.AddStudentButton;
import Model.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Button;

public class AddStudent {
    Shell addStudentDialog;

    public AddStudent(DataBase dataBase, Display display, TableWithStudents myMainTable) {

        RowLayout rowVertLayout = new RowLayout(SWT.VERTICAL);
        RowLayout rowHorLayout = new RowLayout(SWT.HORIZONTAL);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.makeColumnsEqualWidth = true;

        Shell addStudentDialog = new Shell(display, SWT.DIALOG_TRIM);
        addStudentDialog.setText("Добавление студента");
        addStudentDialog.setLayout(rowHorLayout);

        Composite enterName = new Composite(addStudentDialog, SWT.NONE);
        Label enterNameLabel = new Label(enterName, SWT.NONE);
        Text enterNameText = createTextField(enterName, enterNameLabel, "имя", addStudentDialog, gridLayout);

        Composite enterSecondName = new Composite(addStudentDialog, SWT.NONE);
        Label enterSecondNameLabel = new Label(enterSecondName, SWT.NONE);
        Text enterSecondNameText = createTextField(enterSecondName, enterSecondNameLabel, "отчество", addStudentDialog, gridLayout);

        Composite enterSurname = new Composite(addStudentDialog, SWT.NONE);
        Label enterSurnameLabel = new Label(enterSurname, SWT.NONE);
        Text enterSurnameText = createTextField(enterSurname, enterSurnameLabel, "фамилию", addStudentDialog, gridLayout);

        Composite enterCountry = new Composite(addStudentDialog, SWT.NONE);
        Label enterCountryLabel = new Label(enterCountry, SWT.NONE);
        Text enterCountryText = createTextField(enterCountry, enterCountryLabel, "страну", addStudentDialog, gridLayout);

        Composite enterRegion = new Composite(addStudentDialog, SWT.NONE);
        Label enterRegionLabel = new Label(enterCountry, SWT.NONE);
        Text enterRegionText = createTextField(enterRegion, enterRegionLabel, "область", addStudentDialog, gridLayout);

        Composite enterCity = new Composite(addStudentDialog, SWT.NONE);
        Label enterCityLabel = new Label(enterCountry, SWT.NONE);
        Text enterCityText = createTextField(enterCity, enterCityLabel, "город", addStudentDialog, gridLayout);

        Composite enterStreet = new Composite(addStudentDialog, SWT.NONE);
        Label enterStreetLabel = new Label(enterCountry, SWT.NONE);
        Text enterStreetText = createTextField(enterStreet, enterStreetLabel, "улицу", addStudentDialog, gridLayout);

        Composite enterHouse = new Composite(addStudentDialog, SWT.NONE);
        Label enterHouseLabel = new Label(enterCountry, SWT.NONE);
        Text enterHouseText = createTextField(enterHouse, enterHouseLabel, "номер дома", addStudentDialog, gridLayout);

        Composite enterHousing = new Composite(addStudentDialog, SWT.NONE);
        Label enterHousingLabel = new Label(enterCountry, SWT.NONE);
        Text enterHousingText = createTextField(enterHousing, enterHousingLabel, "корпус", addStudentDialog, gridLayout);

        Composite enterFlat = new Composite(addStudentDialog, SWT.NONE);
        Label enterFlatLabel = new Label(enterCountry, SWT.NONE);
        Text enterFlatText = createTextField(enterFlat, enterFlatLabel, "номер квартиры", addStudentDialog, gridLayout);

        Button okButton = new Button(addStudentDialog, SWT.PUSH);
        okButton.setText("Добавить");
        okButton.setSize(100, 150);

        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AddStudentButton addStudentButton = new AddStudentButton(dataBase, enterNameText.getText(),enterSecondNameText.getText(),enterSurnameText.getText(), enterCountryText.getText(),enterRegionText.getText(),enterCityText.getText(),enterStreetText.getText(), enterHouseText.getText(),enterHousingText.getText(),enterFlatText.getText());
                myMainTable.updateTable(myMainTable.getMainTable(), dataBase);
            }
        });

        addStudentDialog.setSize(750 , 500 );
        addStudentDialog.open();
    }

    public Shell getAddStudentDialog() {
        return addStudentDialog;
    }

    public Text createTextField(Composite compositeName, Label labelName, String text, Shell parent, Layout layout) {
        compositeName = new Composite(parent, SWT.NONE);
        compositeName.setLayout(layout);

        labelName = new Label(compositeName, SWT.NONE);
        labelName.setText("Введите "+text+":");

        Text textName = new Text(compositeName, SWT.CENTER);
        textName.setSize(100, 100);

        compositeName.setBounds(10, 10, 150, 100);
        return textName;
    }
}
