package View;

import Controller.StudentController;
import Controller.WorkWithXML;
import Model.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class MyView {
    private TableWithStudents myMainTable;
    private Display display = new Display();
    private Shell shell;
    private WorkWithXML workWithXML = new WorkWithXML();

    public MyView(StudentController studentController){
        shell = new Shell(display);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.verticalSpacing = 50;
        gridLayout.makeColumnsEqualWidth = true;
        shell.setLayout(gridLayout);

        Menu mainMenu = new Menu(shell, SWT.BAR);

        MenuItem readItem = new MenuItem(mainMenu, SWT.NONE);
        readItem.setText("Read XML");
        MenuItem writeItem = new MenuItem(mainMenu, SWT.NONE);
        writeItem.setText("Write XML");

        MenuItem addItem = new MenuItem(mainMenu, SWT.NONE);
        addItem.setText("Add");
        MenuItem searchItem = new MenuItem(mainMenu, SWT.NONE);
        searchItem.setText("Search");
        MenuItem deleteItem = new MenuItem(mainMenu, SWT.NONE);
        deleteItem.setText("Delete");

        ToolBar mainToolBar = new ToolBar(shell, SWT.FLAT | SWT.WRAP);

        ToolItem readXmlItem = new ToolItem(mainToolBar, SWT.PUSH);
        Image readXmlImage = new Image(display, "images/openXML.png");
        readXmlItem.setImage(readXmlImage);
        readXmlItem.setToolTipText("Считать данные из XML");

        ToolItem writeXmlItem = new ToolItem(mainToolBar, SWT.PUSH);
        Image writeXmlImage = new Image(display, "images/saveXML.png");
        writeXmlItem.setImage(writeXmlImage);
        writeXmlItem.setToolTipText("Записать данные в XML");

        ToolItem addStudenItem = new ToolItem(mainToolBar, SWT.PUSH);
        Image addStudenImage = new Image(display, "images/addStudent.png");
        addStudenItem.setImage(addStudenImage);
        addStudenItem.setToolTipText("Добавление студента");

        ToolItem searchStudentItem = new ToolItem(mainToolBar, SWT.PUSH);
        Image searchStudentImage = new Image(display, "images/searchStudent.png");
        searchStudentItem.setImage(searchStudentImage);
        searchStudentItem.setToolTipText("Поиск студента");

        ToolItem deleteStudentItem = new ToolItem(mainToolBar, SWT.PUSH);
        Image deleteStudentImage = new Image(display, "images/deleteStudent.png");
        deleteStudentItem.setImage(deleteStudentImage);
        deleteStudentItem.setToolTipText("Удаление студента");

        myMainTable = new TableWithStudents();
        myMainTable.createTable(shell, studentController);

        Label studentsOnPage = new Label(shell, SWT.NONE);
        Label colOfPages = new Label(shell, SWT.NONE);

        myMainTable.createPaging(shell,  studentController);

        readItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                workWithXML.readXML(shell, studentController);
                myMainTable.updateTableWithPaging(shell, myMainTable.getOnPage(), myMainTable.getCurrentPage(), myMainTable.getMainTable(),  studentController);
            }
        });

        writeItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                workWithXML.writeXML(studentController);
            }
        });

        addItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AddStudent addStudent = new AddStudent(shell,  studentController, display, myMainTable);
            }
        });

        searchItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SearchStudent searchStudent = new SearchStudent(studentController, display);
            }
        });

        deleteItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DeleteStudent deleteStudent = new DeleteStudent(shell, studentController, display, myMainTable);
            }
        });

        readXmlItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                workWithXML.readXML(shell, studentController);
                myMainTable.updateTableWithPaging(shell, myMainTable.getOnPage(), myMainTable.getCurrentPage(), myMainTable.getMainTable(), studentController);
            }
        });

        writeXmlItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                workWithXML.writeXML(studentController);
            }
        });

        addStudenItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AddStudent addStudent = new AddStudent(shell,  studentController, display, myMainTable);
            }
        });

        searchStudentItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SearchStudent searchStudent = new SearchStudent(studentController, display);
            }
        });

        deleteStudentItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DeleteStudent deleteStudent = new DeleteStudent(shell,  studentController, display, myMainTable);
            }
        });

        shell.setMenuBar(mainMenu);
        shell.setSize(1200 , 700 );
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
