import rmi.*;
import service.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry created on port 1099...");

            UserRemoteService userService = new UserRemoteServiceImpl();
            registry.bind("UserService", userService);

            CourseRemoteService courseService = new CourseRemoteServiceImpl();
            registry.bind("CourseService", courseService);

            AnswerRemoteService answerService = new AnswerRemoteServiceImpl();
            registry.bind("AnswerService", answerService);

            QuestionRemoteService questionService = new QuestionRemoteServiceImpl();
            registry.bind("QuestionService", questionService);

            OptionRemoteService optionService = new OptionRemoteServiceImpl();
            registry.bind("OptionService", optionService);

            EnrollmentRemoteService enrollmentService = new EnrollmentRemoteServiceImpl();
            registry.bind("EnrollmentService", enrollmentService);

            CertificateRemoteService certificateService = new CertificateRemoteServiceImpl();
            registry.bind("CertificateService", certificateService);

            TestRemoteService testService = new TestRemoteServiceImpl();
            registry.bind("TestService", testService);

            StudentTestRemoteService studentTestService = new StudentTestRemoteServiceImpl();
            registry.bind("StudentTestService", studentTestService);

            TestStatisticsRemoteService testStatisticsService = new TestStatisticsRemoteServiceImpl();
            registry.bind("TestStatisticsService", testStatisticsService);

            System.out.println("✅ RMI Server is up and all services are registered.");
        } catch (Exception e) {
            System.err.println("❌ RMI Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
