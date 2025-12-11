import React, { useState } from "react";
import { Outlet } from "react-router";
import EmployeeDetails from "./EmployeeDetails";
import "./Style.css";

const employeesData = {
  empNo: 10001,
  firstName: "Jane",
  lastName: "Doe",
  birthDate: "1990-11-25",
  gender: "Female",
  hireDate: "2020-01-15",
  salaries: [
    {
      salary: 60000,
      fromDate: "2020-01-15",
      toDate: "2021-01-15",
    },
    {
      salary: 70000,
      fromDate: "2021-01-15",
      toDate: "2022-01-15",
    },
    {
      salary: 80000,
      fromDate: "2022-01-15",
      toDate: "2023-01-15",
    },
    {
      salary: 85000,
      fromDate: "2023-01-15",
      toDate: "2024-01-15",
    },
    {
      salary: 90000,
      fromDate: "2025-01-15",
      toDate: "9999-01-01",
    },
  ],
  titles: [
    {
      title: "Junior Developer",
      fromDate: "2020-01-15",
      toDate: "2022-01-15",
    },
    {
      title: "Developer",
      fromDate: "2022-01-15",
      toDate: "9999-01-01",
    },
  ],
  deptEmployees: [
    {
      deptNo: "d005",
      deptName: "Development",
      fromDate: "2020-01-15",
      toDate: "9999-01-01",
    },
  ],
  deptManager: [],
};

function Employee() {
  const [employeeNumber, setEmployeeNumber] = useState("");
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  const handleSearch = () => {
    // if (Array.isArray(employeesData)) {
    //   const foundEmployee = employeesData.find(
    //     (emp) => emp.empNo === employeeNumber
    //   );
    //   setSelectedEmployee(foundEmployee);
    // } else {
    //   console.error("employeesData is not an array:", employeesData);
    // }
    setSelectedEmployee(employeesData);
  };

  return (
    <div>
      {/* <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="input1">Input Field 1:</label>
          <input
            type="text"
            id="input1"
            value={inputValue1}
            onChange={handleInputChange1}
          />
        </div>
        <div>
          <label htmlFor="input2">Input Field 2:</label>
          <input
            type="text"
            id="input2"
            value={inputValue2}
            onChange={handleInputChange2}
          />
        </div>
        <button type="submit">Submit</button>
      </form> */}

      <div className="form-container">
        <div className="form-group">
          <label>Enter Employee Number:</label>
          <input
            type="text"
            placeholder="Enter Employee Number"
            value={employeeNumber}
            onChange={(e) => setEmployeeNumber(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
        </div>
      </div>
      {selectedEmployee ? (
        <EmployeeDetails employee={selectedEmployee} />
      ) : (
        <p>No record found.</p>
      )}
    </div>
  );
}

export default Employee;
