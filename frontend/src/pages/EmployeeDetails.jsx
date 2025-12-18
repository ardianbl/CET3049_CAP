import HistoryTable from "./HistoryTable";
import DeptManHistoryTable from "./DeptManHistoryTable.jsx";

const EmployeeDetails = ({ employee }) => {
  return (
    <div>
      <h1>Employee Details</h1>
      <div>
        <div className="details-card">
          <p>
            <strong>Employee Number:</strong> {employee.empNo}
          </p>
          <p>
            <strong>First Name:</strong> {employee.firstName}
          </p>
          <p>
            <strong>Last Name:</strong> {employee.lastName}
          </p>
          <p>
            <strong>Birth Date:</strong>{" "}
            {new Date(employee.birthDate).toLocaleDateString()}
          </p>
          <p>
            <strong>Gender:</strong> {employee.gender}
          </p>
          <p>
            <strong>Hire Date:</strong>{" "}
            {new Date(employee.hireDate).toLocaleDateString()}
          </p>
        </div>

        <h2>Salaries</h2>
        <HistoryTable data={employee.salaries} type="salary" />

        <h2>Titles</h2>
        <HistoryTable data={employee.titles} type="title" />

        <h2>Department History</h2>
        <DeptManHistoryTable data={employee.deptEmp} type="title" />

        <h2>Managerial History</h2>
        <DeptManHistoryTable data={employee.deptManager} type="title" />
      </div>
    </div>
  );
};

export default EmployeeDetails;
