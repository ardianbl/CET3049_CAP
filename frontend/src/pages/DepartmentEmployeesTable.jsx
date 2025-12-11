import React from "react";

const DepartmentEmployeesTable = ({ departmentEmployees }) => {
  // if (!data || data.length === 0) {
  //   return <p>No record found.</p>;
  // }

  return (
    <div>
      <h1>Employees in the department</h1>
      <table className="history-table">
        <thead>
          <tr>
            <th>Employee No</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Hire Date</th>
          </tr>
        </thead>
        <tbody>
          {departmentEmployees.map((departmentEmployee) => (
            <tr key={departmentEmployee.empNo}>
              <td>{departmentEmployee.empNo}</td>
              <td>{departmentEmployee.firstName}</td>
              <td>{departmentEmployee.lastName}</td>
              <td>{departmentEmployee.hireDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DepartmentEmployeesTable;
