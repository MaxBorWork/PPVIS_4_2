import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ForTable {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.verticalSpacing = 50;
        gridLayout.makeColumnsEqualWidth = true;
        shell.setLayout(rowLayout);

        Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn column0 = new TableColumn(table, SWT.NONE);
        column0.setText("ФИО");
        column0.setWidth(150);

        TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setText("Страна");
        column1.setWidth(80);

        TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setText("Область");
        column2.setWidth(100);

        TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setText("Город");
        column3.setWidth(100);

        TableColumn column4 = new TableColumn(table, SWT.NONE);
        column4.setText("Улица");
        column4.setWidth(100);

        TableColumn column5 = new TableColumn(table, SWT.NONE);
        column5.setText("Дом");
        column5.setWidth(50);

        TableColumn column6 = new TableColumn(table, SWT.NONE);
        column6.setText("Корпус");
        column6.setWidth(80);

        TableColumn column7 = new TableColumn(table, SWT.NONE);
        column7.setText("Квартира");
        column7.setWidth(80);

        table.setSize(700, 500);

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
