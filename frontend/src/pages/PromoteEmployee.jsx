import React, { useState } from "react";
import "./Style.css";
// import DepartmentEmployeesTable from "./DepartmentEmployeesTable";

function PromoteEmployee() {
  const [employeeNumber, setEmployeeNumber] = useState("");
  const [newSalary, setNewSalary] = useState("");
  const [newTitle, setNewTitle] = useState("");
  const [newDepartmentNumber, setNewDepartmentNumber] = useState("");
  const [newFromDate, setNewFromDate] = useState("");
  const [isManager, setIsManager] = useState("");
  const [selectedOption, setSelectedOption] = useState("No"); // Set initial selected option

  const handleOptionChange = (event) => {
    setSelectedOption(event.target.value);
  };

  const handleSubmit = () => {
    // if (Array.isArray(allEmployeesData)) {
    //   const foundDepartment = allEmployeesData.find(
    //     (dept) => dept.deptNo === departmentNumber
    //   );
    //   setSelectedDepartment(foundDepartment);
    // } else {
    //   console.error("allEmployeesData is not an array:", allEmployeesData);
    // }
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
            required
          />
          <label>Enter New Salary:</label>
          <input
            type="text"
            placeholder="Enter New Salary"
            value={newSalary}
            onChange={(e) => setNewSalary(e.target.value)}
          />
          <label>Enter New Title:</label>
          <input
            type="text"
            placeholder="Enter New Title"
            value={newTitle}
            onChange={(e) => setNewTitle(e.target.value)}
          />
          <label>Enter New Department Number:</label>
          <input
            type="text"
            placeholder="Enter New Department Number"
            value={newDepartmentNumber}
            onChange={(e) => setNewDepartmentNumber(e.target.value)}
          />
          <label>Enter New From Date:</label>
          <input
            type="text"
            placeholder="Enter New From Date"
            value={newFromDate}
            onChange={(e) => setNewFromDate(e.target.value)}
          />

          <div style={{ display: "flex", gap: "30px" }}>
            <label>Promote to Manager:</label>
            <div>
              <input
                type="radio"
                id="Yes"
                name="isManager"
                value="Yes"
                checked={selectedOption === "Yes"}
                onChange={handleOptionChange}
              />
              <label htmlFor="Yes">Yes</label>
            </div>
            <div>
              <input
                type="radio"
                id="No"
                name="isManager"
                value="No"
                checked={selectedOption === "No"}
                onChange={handleOptionChange}
              />
              <label htmlFor="No">No</label>
            </div>
          </div>
          <button onClick={handleSubmit}>Submit</button>
        </div>
      </div>
      {/* {selectedDepartmentEmployees ? (
        <DepartmentEmployeesTable
          departmentEmployees={selectedDepartmentEmployees}
        />
      ) : (
        <p>No record found.</p>
      )} */}
    </div>
  );
}

export default PromoteEmployee;
