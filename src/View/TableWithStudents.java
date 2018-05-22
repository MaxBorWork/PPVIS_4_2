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
    int onPage = 10;

    /*TableWithStudents(Shell shell, DataBase dataBase) {

        mainTable = createTable(shell, dataBase);
        createPaging(shell, dataBase);
    }*/

    public Table createTable(Shell shell, DataBase dataBase) {

        mainTable = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

        //mainTable.setBounds(80,50,1300,600);

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

        mainTable.setSize(700, 500);
        //updateTable(mainTable, dataBase);
        if (dataBase.studentList.size() != 0) {
            updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
        }
        return mainTable;
    }

    public void createPaging(Shell shell, DataBase dataBase) {
        Composite paging = new Composite(shell, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 6;
        gridLayout.makeColumnsEqualWidth = true;
        paging.setLayout(gridLayout);

        Button firstPageBtn = new Button(paging, SWT.PUSH);
        firstPageBtn.setText("Первая");

        Button prevPageBtn = new Button(paging, SWT.PUSH);
        prevPageBtn.setText("Предыдущая");

        Button nextPageBtn = new Button(paging, SWT.PUSH);
        nextPageBtn.setText("Следующая");

        Button lastPageBtn = new Button(paging, SWT.PUSH);
        lastPageBtn.setText("Последняя");

        Label chooseStudOnPageText = new Label(paging, SWT.NONE);
        chooseStudOnPageText.setText("На странице: ");

        Combo chooseStudOnPageCombo = new Combo(paging, SWT.DROP_DOWN);
        chooseStudOnPageCombo.add("10", 0);
        chooseStudOnPageCombo.select(0);
        chooseStudOnPageCombo.add("20", 1);
        chooseStudOnPageCombo.add("30", 2);
        chooseStudOnPageCombo.add("50", 3);

        chooseStudOnPageCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (chooseStudOnPageCombo.getSelectionIndex() == 1) {
                    onPage = 20;
                    if (dataBase.studentList.size() >= onPage)
                    updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
                }
                else if (chooseStudOnPageCombo.getSelectionIndex() == 2) {
                    onPage = 30;
                    if (dataBase.studentList.size() >= onPage)
                    updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
                }

                else if (chooseStudOnPageCombo.getSelectionIndex() == 3) {
                    onPage = 50;
                    if (dataBase.studentList.size() >= onPage)
                    updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
                }
                else {
                    onPage = 10;
                    if (dataBase.studentList.size() >= onPage)
                        updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
                }
            }
        });

        firstPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                currentPage = 1;
                updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
            }
        });

        prevPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
                }
            }
        });

        nextPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentPage < (numPages(dataBase, 10) - 1)) {
                    currentPage++;
                    updateTableWithPaging(onPage, currentPage, mainTable, dataBase);
                }
                else if (currentPage == (numPages(dataBase, 10) - 1)) {
                    currentPage++;
                    updateTableWithPagingLastPage(onPage, currentPage, mainTable, dataBase);
                }
            }
        });

        lastPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                currentPage = numPages(dataBase, 10);
                updateTableWithPagingLastPage(onPage, currentPage, mainTable, dataBase);
            }
        });
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

    public void updateTableWithPaging(int onPage, int curPage, Table mainTable, DataBase dataBase) {
        mainTable.removeAll();
        for (int studentIndex=onPage*(curPage-1), addressIndex=onPage*(curPage-1); studentIndex<(onPage*curPage) && addressIndex<(onPage*curPage); studentIndex++, addressIndex++) {
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

    public void updateTableWithPagingLastPage(int onPage, int curPage, Table mainTable, DataBase dataBase) {
        mainTable.removeAll();
        for (int studentIndex=onPage*(curPage-1), addressIndex=onPage*(curPage-1); studentIndex<dataBase.studentList.size() && addressIndex<dataBase.addressList.size(); studentIndex++, addressIndex++) {
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
        int numOfPages = dataBase.studentList.size()/studentsOnPage + 1;

        return numOfPages;
    }

    public Table getMainTable() {
        return mainTable;
    }

    public int getOnPage() {
        return onPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
