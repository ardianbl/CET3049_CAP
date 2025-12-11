import { Outlet } from "react-router";
import EmployeeDetails from "./EmployeeDetails";
import HistoryTable from "./HistoryTable";
import "./Welcome.css"; // Optional: for basic styling

const employeeData = {
  id: 101,
  name: "Jane Doe",
  position: "Senior Software Engineer",
  department: "Engineering",
  hireDate: "2020-01-15",
  salaryHistory: [
    {
      id: 1,
      amount: 60000,
      effectiveDate: "2020-01-15",
      reason: "Initial Hire",
    },
    {
      id: 2,
      amount: 75000,
      effectiveDate: "2021-01-15",
      reason: "Annual Review/Raise",
    },
    { id: 3, amount: 90000, effectiveDate: "2022-03-01", reason: "Promotion" },
    {
      id: 4,
      amount: 95000,
      effectiveDate: "2024-01-15",
      reason: "Annual Review/Raise",
    },
  ],
  titleHistory: [
    { id: 1, title: "Software Engineer I", effectiveDate: "2020-01-15" },
    { id: 2, title: "Software Engineer II", effectiveDate: "2021-06-01" },
    { id: 3, title: "Senior Software Engineer", effectiveDate: "2022-03-01" },
  ],
};

function Welcome() {
  return (
    <div className="Welcome">
      <h1>Employee Details</h1>
      <div className="employee-container">
        <EmployeeDetails employee={employeeData} />

        <h2>Salaries</h2>
        <HistoryTable data={employeeData.salaryHistory} type="salary" />

        <h2>Titles</h2>
        <HistoryTable data={employeeData.titleHistory} type="title" />
      </div>
    </div>
  );
}

export default Welcome;
