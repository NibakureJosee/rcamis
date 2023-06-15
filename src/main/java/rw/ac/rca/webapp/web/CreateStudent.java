package rw.ac.rca.webapp.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.dao.impl.StudentDAOImpl;
import rw.ac.rca.webapp.orm.Address;
import rw.ac.rca.webapp.orm.Student;
import rw.ac.rca.webapp.util.UserRole;

/**
 * Servlet implementation class CreateStudent
 */
public class CreateStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = StudentDAOImpl.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStudent() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        if (pageRedirect != null&& pageRedirect.equals("createstudent")) {

                if (request.getParameter("saveStd") != null) {
                    Student student = new Student();
                    String studentfname = request.getParameter("firstName");
                    String studentlname = request.getParameter("lastName");
                    String studentgender = request.getParameter("gender");
                    String studentemail = request.getParameter("email");
                    String stdDOB = request.getParameter("dateOfBirth");
                    String phoneNumber=request.getParameter("phoneNumber");
                    int stdaddress = Integer.parseInt(request.getParameter("address"));
//                    Address studentaddress = new Address(stdaddress);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date studentdob = null;
                    try{
                        studentdob = simpleDateFormat.parse(stdDOB);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }



                    try {

                        student.setFirstName(studentfname);
                        student.setLastName(studentlname);
//                        student.setGender(studentgender);
                        student.setDateOfBirth(studentdob);
//                        student.setEmail(studentemail);
                        student.setPhoneNumber(phoneNumber);
//                        student.setAddress(studentaddress);

                        studentDAO.saveOrUpdateStudent(student);

                        httpSession.setAttribute("message", "Created successfully");
                    } catch (Exception e) {
                        httpSession.setAttribute("message", "Can't Create Student");
                    }

                }

            }
            UserRole[] userRoles = UserRole.values();
            httpSession.setAttribute("userRoles", userRoles);
            request.getRequestDispatcher("WEB-INF/createstudent.jsp").forward(
                    request, response);
        }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}