package View;

import Controller.StudentController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Composite;

public class TableWithStudents {
    private Table mainTable;
    private int currentPage = 1;
    private int onPage = 10;

    public void createTable(Shell shell, StudentController studentController) {

        mainTable = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        mainTable.setHeaderVisible(true);
        mainTable.setLinesVisible(true);

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
        if (studentController.getStudentListSize() != 0) {
            updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
        }
    }

    public void createPaging(Shell shell, StudentController studentController) {
        Composite pagingComposite = new Composite(shell, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 7;
        pagingComposite.setLayout(gridLayout);

        Button firstPageBtn = new Button(pagingComposite, SWT.PUSH);
        firstPageBtn.setText("Первая");

        Button prevPageBtn = new Button(pagingComposite, SWT.PUSH);
        prevPageBtn.setText("Предыдущая");

        Button nextPageBtn = new Button(pagingComposite, SWT.PUSH);
        nextPageBtn.setText("Следующая");

        Button lastPageBtn = new Button(pagingComposite, SWT.PUSH);
        lastPageBtn.setText("Последняя");

        Label chooseStudOnPageText = new Label(pagingComposite, SWT.NONE);
        chooseStudOnPageText.setText("На странице: ");

        Combo chooseStudOnPageCombo = new Combo(pagingComposite, SWT.DROP_DOWN);
        chooseStudOnPageCombo.add("10", 0);
        chooseStudOnPageCombo.select(0);
        chooseStudOnPageCombo.add("20", 1);
        chooseStudOnPageCombo.add("30", 2);
        chooseStudOnPageCombo.add("50", 3);

        Composite infoAboutCol = new Composite(shell, SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 2;
        infoAboutCol.setLayout(rowLayout);
        infoAboutCol.setBounds(10, 10, 600, 100);
        //infoAboutCol.setSize(600, 50);

        Label studentsOnPage = new Label(infoAboutCol, SWT.NONE);
        //studentsOnPage.setSize(300, 50);
        studentsOnPage.setBounds(10,10,600, 100);
        studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
        //studentsOnPage.pack();

        Label colOfPages = new Label(infoAboutCol, SWT.NONE);
        colOfPages.setSize(600, 50);
        colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
        colOfPages.pack();

        chooseStudOnPageCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (chooseStudOnPageCombo.getSelectionIndex() == 1) {
                    onPage = 20;
                    if (studentController.getStudentListSize() >= onPage)
                        updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    else updateTable(shell, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
                else if (chooseStudOnPageCombo.getSelectionIndex() == 2) {
                    onPage = 30;
                    if (studentController.getStudentListSize() >= onPage)
                        updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    else updateTable(shell, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }

                else if (chooseStudOnPageCombo.getSelectionIndex() == 3) {
                    onPage = 50;
                    if (studentController.getStudentListSize() >= onPage)
                        updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    else updateTable(shell, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
                else {
                    onPage = 10;
                    if (studentController.getStudentListSize() >= onPage)
                        updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    else updateTable(shell, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
            }
        });

        firstPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                currentPage = 1;
                updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                if (studentController.getAddressListSize() < onPage)
                    studentsOnPage.setText("Записи " + (onPage*(currentPage-1)+1) + "-" + studentController.getAddressListSize() + " из " + studentController.getAddressListSize());
                else
                    studentsOnPage.setText("Зап. " + (onPage*(currentPage-1)+1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
            }
        });

        prevPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentPage > 2) {
                    currentPage--;
                    updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
                else if (currentPage == 2) {
                    currentPage--;
                    updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + (onPage*(currentPage-1)+1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
            }
        });

        nextPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (currentPage < (numPages(studentController, onPage) - 1)) {
                    currentPage++;
                    updateTableWithPaging(shell, onPage, currentPage, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
                else if (currentPage == (numPages(studentController, onPage) - 1)) {
                    currentPage++;
                    updateTableWithPagingLastPage(shell, onPage, currentPage, mainTable, studentController);
                    studentsOnPage.setText("Зап. " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                    colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
                }
            }
        });

        lastPageBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                currentPage = numPages(studentController, onPage);
                updateTableWithPagingLastPage(shell, onPage, currentPage, mainTable, studentController);
                studentsOnPage.setText("Записи " + onPage*(currentPage-1) + "-" + (onPage*currentPage) + " из " + studentController.getAddressListSize());
                colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
            }
        });
    }

    public void updateTable(Shell shell, Table mainTable, StudentController studentController) {
        mainTable.removeAll();
        for (int studentIndex = 0, addressIndex = 0; studentIndex < studentController.getAddressListSize() && addressIndex < studentController.getAddressListSize(); studentIndex++, addressIndex++) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, studentController.getStudentFromDataBase(studentIndex).getFullName());
            item.setText(1, studentController.getAddressFromDataBase(addressIndex).getCountry());
            item.setText(2, studentController.getAddressFromDataBase(addressIndex).getRegion());
            item.setText(3, studentController.getAddressFromDataBase(addressIndex).getCity());
            item.setText(4, studentController.getAddressFromDataBase(addressIndex).getStreet());
            item.setText(5, String.valueOf(studentController.getAddressFromDataBase(addressIndex).getHouse()));
            item.setText(6, studentController.getAddressFromDataBase(addressIndex).getHousing());
            item.setText(7, String.valueOf(studentController.getAddressFromDataBase(addressIndex).getFlat()));
        }
        //createPaging(shell, studentController);
    }

    public void updateTableWithPaging(Shell shell, int onPage, int curPage, Table mainTable, StudentController studentController) {
        mainTable.removeAll();
        for (int studentIndex=onPage*(curPage-1), addressIndex=onPage*(curPage-1); studentIndex<(onPage*curPage) && addressIndex<(onPage*curPage); studentIndex++, addressIndex++) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, studentController.getStudentFromDataBase(studentIndex).getFullName());
            item.setText(1, studentController.getAddressFromDataBase(addressIndex).getCountry());
            item.setText(2, studentController.getAddressFromDataBase(addressIndex).getRegion());
            item.setText(3, studentController.getAddressFromDataBase(addressIndex).getCity());
            item.setText(4, studentController.getAddressFromDataBase(addressIndex).getStreet());
            item.setText(5, String.valueOf(studentController.getAddressFromDataBase(addressIndex).getHouse()));
            item.setText(6, studentController.getAddressFromDataBase(addressIndex).getHousing());
            item.setText(7, String.valueOf(studentController.getAddressFromDataBase(addressIndex).getFlat()));
        }
        //createPaging(shell, studentController);
    }

    private void updateTableWithPagingLastPage(Shell shell, int onPage, int curPage, Table mainTable, StudentController studentController) {
        mainTable.removeAll();
        for (int studentIndex = onPage*(curPage-1), addressIndex = onPage*(curPage-1); studentIndex < studentController.getStudentListSize() && addressIndex < studentController.getAddressListSize(); studentIndex++, addressIndex++) {
            TableItem item = new TableItem(mainTable, SWT.NONE);
            item.setText(0, studentController.getStudentFromDataBase(studentIndex).getFullName());
            item.setText(1, studentController.getAddressFromDataBase(addressIndex).getCountry());
            item.setText(2, studentController.getAddressFromDataBase(addressIndex).getRegion());
            item.setText(3, studentController.getAddressFromDataBase(addressIndex).getCity());
            item.setText(4, studentController.getAddressFromDataBase(addressIndex).getStreet());
            item.setText(5, String.valueOf(studentController.getAddressFromDataBase(addressIndex).getHouse()));
            item.setText(6, studentController.getAddressFromDataBase(addressIndex).getHousing());
            item.setText(7, String.valueOf(studentController.getAddressFromDataBase(addressIndex).getFlat()));
        }
        //createPaging(shell, studentController);
    }

    private int numPages(StudentController studentController, int studentsOnPage){
        return studentController.getStudentListSize()/studentsOnPage + 1;
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

    public void changeLabelText(Label studentsOnPage, Label colOfPages, StudentController studentController) {
        studentsOnPage.setText("Записи " + onPage*(currentPage-1) + " - " + (onPage*currentPage) + " из " + studentController.getAddressListSize());
        colOfPages.setText("Страница " + currentPage + " из " + numPages(studentController, onPage));
    }
}
