import React, {useState} from "react";
import EmployeeDetails from "./EmployeeDetails";
import "./Style.css";

function Employees() {
    const [empNo, setEmpNo] = useState("");
    const [employee, setEmployee] = useState(null);
    const [error, setError] = useState(null)

    const searchUser = () => {
        if (empNo.trim() === "") {
            setError("Please enter Employee Number.");
            return;
        }
        setEmployee(null);

        setError(null);

        fetch(`http://localhost:8080/api/employees/${empNo}`)
            .then(async res => {
                if (!res.ok) {
                    const msg = await res.text();
                    throw new Error(msg);
                }
                return res.json()
            })
            .then(data => setEmployee(data))
            .catch(err => setError(err.message));
    };

    return (
        <div>
            <div className="form-container">
                <div className="form-group">
                    <label>Enter Employee Number:</label>
                    <input
                        type="number"
                        placeholder="e.g. 10001"
                        value={empNo}
                        onChange={(e) => {
                            setEmpNo(e.target.value);
                            setError(null);
                        }}
                    />
                    <button onClick={searchUser}>Search</button>
                </div>
            </div>

            {employee ? (
                <EmployeeDetails employee={employee}/>
            ) : (
                <>{error && <p style={{color: "red"}}>{error}</p>}</>
            )}
        </div>
    );
}

export default Employees;
