import React, { useState } from "react";
import "./Style.css";
import DepartmentEmployeesTable from "./DepartmentEmployeesTable";

const departmentEmployeesData = [
  {
    empNo: 10042,
    firstName: "Magy",
    lastName: "Stamatiou",
    hireDate: "21-03-1993",
  },
  {
    empNo: 10050,
    firstName: "Yinghua",
    lastName: "Dredge",
    hireDate: "25-12-1990",
  },
  {
    empNo: 10059,
    firstName: "Alejandro",
    lastName: "McAlpine",
    hireDate: "26-06-1991",
  },
  {
    empNo: 10080,
    firstName: "Premal",
    lastName: "Baek",
    hireDate: "19-11-1985",
  },
  {
    empNo: 10132,
    firstName: "Ayakannu",
    lastName: "Skrikant",
    hireDate: "30-10-1994",
  },
  {
    empNo: 10144,
    firstName: "Marla",
    lastName: "Brendel",
    hireDate: "14-10-1985",
  },
  {
    empNo: 10146,
    firstName: "Chenyi",
    lastName: "Syang",
    hireDate: "28-06-1988",
  },
  {
    empNo: 10147,
    firstName: "Kazuhito",
    lastName: "Encarnacion",
    hireDate: "21-08-1986",
  },
  {
    empNo: 10165,
    firstName: "Miyeon",
    lastName: "Macedo",
    hireDate: "17-05-1988",
  },
  {
    empNo: 10173,
    firstName: "Shrikanth",
    lastName: "Mahmud",
    hireDate: "21-03-1992",
  },
  {
    empNo: 10177,
    firstName: "Pragnesh",
    lastName: "Iisaka",
    hireDate: "06-02-1993",
  },
  {
    empNo: 10180,
    firstName: "Shaw",
    lastName: "Wendorf",
    hireDate: "25-02-1986",
  },
  {
    empNo: 10186,
    firstName: "Shigehito",
    lastName: "Kropatsch",
    hireDate: "28-03-1986",
  },
  {
    empNo: 10187,
    firstName: "Tommaso",
    lastName: "Narwekar",
    hireDate: "01-06-1991",
  },
  {
    empNo: 10194,
    firstName: "Josyula",
    lastName: "Hofmeyr",
    hireDate: "15-05-1989",
  },
  {
    empNo: 10196,
    firstName: "Marc",
    lastName: "Hellwagner",
    hireDate: "16-11-1994",
  },
  {
    empNo: 10209,
    firstName: "Yolla",
    lastName: "Ellozy",
    hireDate: "23-11-1991",
  },
  {
    empNo: 10220,
    firstName: "Kish",
    lastName: "Fasbender",
    hireDate: "25-06-1992",
  },
  {
    empNo: 10241,
    firstName: "Ortrud",
    lastName: "Murillo",
    hireDate: "12-06-1988",
  },
  {
    empNo: 10263,
    firstName: "Takahiro",
    lastName: "Waterhouse",
    hireDate: "05-02-1994",
  },
];

function DepartmentEmployees() {
  const [departmentNumber, setDepartmentNumber] = useState("");
  const [selectedDepartmentEmployees, setSelectedDepartmentEmployees] =
    useState(null);
  const [pageNumber, setPageNumber] = useState("");
  const [selectedPage, setSelectedPage] = useState(null);

  const handleSearch = () => {
    // if (Array.isArray(allEmployeesData)) {
    //   const foundDepartment = allEmployeesData.find(
    //     (dept) => dept.deptNo === departmentNumber
    //   );
    //   setSelectedDepartment(foundDepartment);
    // } else {
    //   console.error("allEmployeesData is not an array:", allEmployeesData);
    // }
    setSelectedDepartmentEmployees(departmentEmployeesData);
    setSelectedPage(1);
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
          <label>Enter Department Number:</label>
          <input
            type="text"
            placeholder="Enter Department Number"
            value={departmentNumber}
            onChange={(e) => setDepartmentNumber(e.target.value)}
          />
          <label>Enter Page Number:</label>
          <input
            type="text"
            placeholder="Enter Page Number"
            value={pageNumber}
            onChange={(e) => setPageNumber(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
        </div>
      </div>
      {selectedDepartmentEmployees ? (
        <DepartmentEmployeesTable
          departmentEmployees={selectedDepartmentEmployees}
        />
      ) : (
        <p>No record found.</p>
      )}
    </div>
  );
}

export default DepartmentEmployees;
