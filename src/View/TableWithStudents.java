package View;

import Model.Address;
import Model.DataBase;
import Model.Student;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Composite;


public class TableWithStudents {
    Table mainTable;
    int currentPage = 1;

    TableWithStudents(Shell shell, DataBase dataBase) {

        mainTable = createTable(shell, dataBase);

        Composite paging = new Composite(shell, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 5;
        gridLayout.makeColumnsEqualWidth = true;
        paging.setLayout(gridLayout);


        Button firstPageBtn = new Button(paging, SWT.PUSH);
        firstPageBtn.setText("Первая");
        firstPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                currentPage = 1;
                updateTableWithPaging(10, currentPage, mainTable, dataBase);
            }
        });

        Button prevPageBtn = new Button(paging, SWT.PUSH);
        prevPageBtn.setText("Предыдущая");
        prevPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTableWithPaging(10, currentPage, mainTable, dataBase);
                }
            }
        });

        Button nextPageBtn = new Button(paging, SWT.PUSH);
        nextPageBtn.setText("Следующая");
        nextPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentPage < numPages(dataBase, 10)) {
                    currentPage++;
                    updateTableWithPaging(10, currentPage, mainTable, dataBase);
                }
            }
        });

        Button lastPageBtn = new Button(paging, SWT.PUSH);
        lastPageBtn.setText("Последняя");
        lastPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                currentPage = numPages(dataBase, 10);
                updateTableWithPaging(10, currentPage, mainTable, dataBase);
            }
        });
    }

    public Table createTable(Shell shell, DataBase dataBase) {

        Table mainTable = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        mainTable.setBounds(80,50,1300,600);
        /*String[] titles = {"ФИО","Страна","Область","Город","Улица","Дом","Корпус","Квартира"};

        for (String title : titles) {
            TableColumn column = new TableColumn(mainTable, SWT.NONE);
            column.setText(title);
            column.setWidth(100);
        }*/

        TableColumn column0 = new TableColumn(mainTable, SWT.NONE);
        column0.setText("ФИО");
        column0.setWidth(150);

        TableColumn column1 = new TableColumn(mainTable, SWT.NONE);
        column1.setText("Страна");
        column1.setWidth(80);

        TableColumn column2 = new TableColumn(mainTable, SWT.NONE);
        column2.setText("Область");
        column2.setWidth(100);

        TableColumn column3 = new TableColumn(mainTable, SWT.NONE);
        column3.setText("Город");
        column3.setWidth(100);

        TableColumn column4 = new TableColumn(mainTable, SWT.NONE);
        column4.setText("Улица");
        column4.setWidth(100);

        TableColumn column5 = new TableColumn(mainTable, SWT.NONE);
        column5.setText("Дом");
        column5.setWidth(50);

        TableColumn column6 = new TableColumn(mainTable, SWT.NONE);
        column6.setText("Корпус");
        column6.setWidth(80);

        TableColumn column7 = new TableColumn(mainTable, SWT.NONE);
        column7.setText("Квартира");
        column7.setWidth(80);

        updateTable(mainTable, dataBase);
        return mainTable;
    }

    public void updateTable(Table mainTable, DataBase dataBase) {
        mainTable.removeAll();
        for (int studentIndex=0, addressIndex=0; studentIndex<dataBase.studentList.size() && addressIndex<dataBase.addressList.size(); studentIndex++, addressIndex++) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, dataBase.getStudent(studentIndex).getFullName());
            item.setText(1, dataBase.getAddress(addressIndex).getCountry());
            item.setText(2, dataBase.getAddress(addressIndex).getRegion());
            item.setText(3, dataBase.getAddress(addressIndex).getCity());
            item.setText(4, dataBase.getAddress(addressIndex).getStreet());
            item.setText(5, dataBase.getAddress(addressIndex).getHouse());
            item.setText(6, dataBase.getAddress(addressIndex).getHousing());
            item.setText(7, dataBase.getAddress(addressIndex).getFlat());
        }
    }

    public void updateTableWithPaging(int onPage, int curPAge, Table mainTable, DataBase dataBase) {
        mainTable.removeAll();
        for (int studentIndex=(onPage*curPAge-onPage), addressIndex=(onPage*curPAge-onPage); studentIndex<(onPage*curPAge) && addressIndex<(onPage*curPAge); studentIndex++, addressIndex++) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, dataBase.getStudent(studentIndex).getFullName());
            item.setText(1, dataBase.getAddress(addressIndex).getCountry());
            item.setText(2, dataBase.getAddress(addressIndex).getRegion());
            item.setText(3, dataBase.getAddress(addressIndex).getCity());
            item.setText(4, dataBase.getAddress(addressIndex).getStreet());
            item.setText(5, dataBase.getAddress(addressIndex).getHouse());
            item.setText(6, dataBase.getAddress(addressIndex).getHousing());
            item.setText(7, dataBase.getAddress(addressIndex).getFlat());
        }
    }

    private int numPages(DataBase dataBase, int studentsOnPage){
        int numOfPages = dataBase.studentList.size()/studentsOnPage;

        return numOfPages;
    }

    public Table getMainTable() {
        return mainTable;
    }

}
