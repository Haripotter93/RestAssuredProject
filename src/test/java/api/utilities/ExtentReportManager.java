package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String reportPath = System.getProperty("user.dir") + "/reports/Test-Report-" + timeStamp + ".html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setReportName("API Automation Results");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Project", "API Automation");
		extent.setSystemInfo("Tester", "QA Team");
		extent.setSystemInfo("Environment", "QA");
	}

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped");
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
}