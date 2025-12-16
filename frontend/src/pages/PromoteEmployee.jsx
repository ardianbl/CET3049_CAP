import React, {useState} from "react";
import "./Style.css";
import DepartmentEmployeesTable from "./DepartmentEmployeesTable.jsx";
import PromotionDetails from "./PromotionDetails.jsx";

// import DepartmentEmployeesTable from "./DepartmentEmployeesTable";

function PromoteEmployee() {
    const [employeeNumber, setEmployeeNumber] = useState("");
    const [newSalary, setNewSalary] = useState("");
    const [newTitle, setNewTitle] = useState("");
    const [newDepartmentNumber, setNewDepartmentNumber] = useState("");
    const [newFromDate, setNewFromDate] = useState("");
    const [isManager, setIsManager] = useState("");
    const [selectedOption, setSelectedOption] = useState("No"); // Set initial selected option
    const [error, setError] = useState(null);

    const handleOptionChange = (event) => {
        setSelectedOption(event.target.value);
    };

    const handleSubmit = () => {
    };

    return (
        <div>
            <div className="form-container">
                <div className="form-group">
                    <label>Enter Employee Number:</label>
                    <input
                        type="number"
                        min={1}
                        max={99999999999}
                        placeholder={"e.g. 10001"}
                        value={employeeNumber}
                        onChange={(e) => setEmployeeNumber(e.target.value)}
                        required={true}
                    />
                    <label>Enter New Salary:</label>
                    <input
                        type="number"
                        min={1}
                        max={99999999999}
                        placeholder="e.g. $100.00"
                        value={newSalary}
                        onChange={(e) => setNewSalary(e.target.value)}
                        required={true}
                    />
                    <label>Enter New Title:</label>
                    <input
                        type="text"
                        maxLength={50}
                        value={newTitle}
                        onChange={(e) => setNewTitle(e.target.value)}
                        required={true}
                    />
                    <label>Enter New Department Number:</label>
                    <input
                        type="text"
                        maxLength={4}
                        pattern={"^[a-zA-Z][0-9]{3}$"}
                        placeholder="e.g. D001 (optional)"
                        value={newDepartmentNumber}
                        onChange={(e) => setNewDepartmentNumber(e.target.value)}
                    />
                    <label>Enter Starting Date:</label>
                    <input
                        type={"date"}
                        placeholder="In YYYY-MM-DD format, e.g. 2025-12-31"
                        value={newFromDate}
                        onChange={(e) => setNewFromDate(e.target.value)}
                    />

                    <div style={{display: "flex", gap: "30px"}}>
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

            {error && <p style={{color: "red"}}>{error}</p>}

            <>
                <PromotionDetails
                    employee={employees}
                    disabled={loading}/>
            </>
        </div>
    );
}

export default PromoteEmployee;
