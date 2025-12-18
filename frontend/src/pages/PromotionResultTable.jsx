import React from "react";
import historyTable from "./HistoryTable.jsx";

const PromotionResultTable = ({before, after}) => {
    if (!before || !after) return null;

    const FIELD_CONFIG = [
        {label: "Title", key: "newTitle"},
        {label: "Department", key: "newDeptNo"},
        {label: "Salary", key: "newSalary", format: v => `$${v}`}
    ];


    return (
        <table className={"history-table"}>
            <thead>
            <tr>
                <th></th>
                <th>Before Promotion</th>
                <th>After Promotion</th>
            </tr>
            </thead>
            <tbody>
            {FIELD_CONFIG.map(({label, key, format}) => {
                const beforeVal = before[key];
                const afterVal = after[key];

                const changed = beforeVal !== afterVal;

                return (
                    <tr key={key}>
                        <td><strong>{label}</strong></td>
                        <td style={{backgroundColor: changed ? "#f0fdfa" : "transparent"}}>
                            {format ? format(beforeVal) : beforeVal}
                        </td>
                        <td style={{backgroundColor: changed ? "#f0fdfa" : "transparent"}}>
                            {format ? format(afterVal) : afterVal}
                        </td>
                    </tr>
                );
            })}
            </tbody>
        </table>
    );
};

export default PromotionResultTable;

