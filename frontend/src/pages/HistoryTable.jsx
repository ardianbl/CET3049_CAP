import React from "react";

const HistoryTable = ({ data, type }) => {
  if (!data || data.length === 0) {
    return <p>No record found.</p>;
  }

  return (
    <table className="history-table">
      <thead>
        <tr>
          <th>From Date</th>
          <th>To Date</th>
          {type === "salary" ? <th>Salary</th> : <th>Title</th>}
        </tr>
      </thead>
      <tbody>
        {data.map((employee) => (
          <tr key={employee.empNo}>
            <td>{new Date(employee.fromDate).toLocaleDateString()}</td>
            <td>{new Date(employee.toDate).toLocaleDateString()}</td>
            <td>
              {type === "salary"
                ? `$${employee.salary.toLocaleString()}`
                : employee.title}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default HistoryTable;
