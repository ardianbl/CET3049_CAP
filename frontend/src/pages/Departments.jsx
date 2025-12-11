import "./Style.css";

const departmentsData = [
  { deptNo: "d001", deptName: "Marketing" },
  { deptNo: "d002", deptName: "Finance" },
  { deptNo: "d003", deptName: "Human Resources" },
  { deptNo: "d004", deptName: "Production" },
  { deptNo: "d005", deptName: "Development" },
  { deptNo: "d006", deptName: "Quality Management" },
  { deptNo: "d007", deptName: "Sales" },
  { deptNo: "d008", deptName: "Research" },
  { deptNo: "d009", deptName: "Customer Service" },
];

function Departments() {
  return (
    <div>
      <h1>All Departments</h1>
      <table className="departments-table">
        <thead>
          <tr>
            <th>Department Number</th>
            <th>Department Name</th>
          </tr>
        </thead>
        <tbody>
          {departmentsData.map((row) => (
            <tr key={row.deptNo}>
              <td>{row.deptNo}</td>
              <td>{row.deptName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Departments;
