import java.util.ArrayList;

public class StaffLogin
{
    private ArrayList<Staff> staffList;

    public StaffLogin()
    {
        staffList = new ArrayList<>();

        // Default staff (for testing)
        staffList.add(new Staff("0139022", "Mohd Naziril Bin Nuriqmal", "Manager", "123"));
        staffList.add(new Staff("0139019", "Danish Haikal Bin Mohd Sani", "Cashier", "456"));
        staffList.add(new Staff("M44100653", "Tan Yihan", "Staff", "789"));
    }

    public void addStaff(Staff staff)
    {
        staffList.add(staff);
    }

    public Staff login(String enteredId, String enteredPassword)
    {
        for (Staff staff : staffList)
        {
            if (staff.login(enteredId, enteredPassword))
            {
                System.out.println("Login successful! Welcome " + staff.getName());
                return staff; 
            }
        }
        System.out.println("Login failed: Invalid Staff ID or Password.");
        return null; 
    }
}
