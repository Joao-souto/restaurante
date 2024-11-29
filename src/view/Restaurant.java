package src.view;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Restaurant extends JFrame{
  public static enum Page{
    HOME,
    REGISTER,
    LOGIN,
    REGISTER_EMPLOYEE,
    REGISTER_USER,
    EMPLOYEE_HOME,
    USER_HOME,
    USER_ORDER,
    USER_RESERVE,
  };

  public void goToPage(Page toPage){
    JPanel page=null;

    if(toPage==Page.HOME) page=new HomePage(this);
    else if(toPage==Page.LOGIN) page=new LoginPage(this);
    else if(toPage==Page.REGISTER) page=new RegisterPage(this);
    else if(toPage==Page.REGISTER_EMPLOYEE) page=new RegisterEmployeePage(this);
    else if(toPage==Page.REGISTER_USER) page=new RegisterUserPage(this);
    else if(toPage==Page.USER_HOME) page=new UserHomePage(this);
    else if(toPage==Page.USER_ORDER) page=new UserOrderPage(this);
    else if(toPage==Page.USER_RESERVE) page=new UserReservePage(this);
    else if(toPage==Page.EMPLOYEE_HOME) page=new EmployeeHomePage(this);

    this.setContentPane(page);
    this.revalidate();
  }

  public void initialize(){
    this.setTitle("Restaurante");
    this.setSize(800,800);
    this.setVisible(true);
    this.goToPage(Page.HOME);
  }
}
