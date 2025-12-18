import React from "react";

const DeptManHistoryTable = ({data, type}) => {
    if (!data || data.length === 0) {
        return <p>No record found.</p>;
    }

    return (
        <table className="history-table">
            <thead>
            <tr>
                <th>Dept Number</th>
                <th>From Date</th>
                <th>To Date</th>
            </tr>
            </thead>
            <tbody>
            {data.map((employee) => (
                <tr key={employee.empNo}>
                    <td>{new String(employee.deptNo).toUpperCase()}</td>
                    <td>{new Date(employee.fromDate).toLocaleDateString()}</td>
                    <td>{new Date(employee.toDate).toLocaleDateString()}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};

export default DeptManHistoryTable;
