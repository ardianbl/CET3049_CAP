import React, {useEffect, useState} from "react";
import "./Style.css";
import DepartmentEmployeesTable from "./DepartmentEmployeesTable";
import DepartmentSelect from "../components/DepartmentSelect.jsx";

function DepartmentEmployees() {
    const [deptNo, setDeptNo] = useState("");
    const [page, setPage] = useState(1);
    const [employees, setEmployees] = useState([]);
    const [totalPages, setTotalPages] = useState(0);

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [hasSearched, setHasSearched] = useState(false);

    const [departments, setDepartments] = useState([]);
    const [loadingDepts, setLoadingDepts] = useState(true);


    useEffect(() => {
        fetchDepartments();
    }, []);

    const fetchDepartments = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/departments");

            if (!res.ok)
                throw new Error(await res.text());

            const data = await res.json();
            setDepartments(data);
        } catch (e) {
            setError(e.message);
        } finally {
            setLoadingDepts(false);
        }
    };

    const fetchEmployees = async (page = 1) => {
        if (deptNo.trim() === "") {
            setError("Please enter a Department Number.");
            return;
        }

        try {
            const response = await fetch(
                `http://localhost:8080/api/employees?deptNo=${deptNo}&page=${page || 1}`
            );

            if (!response.ok) {
                const msg = await response.text();
                throw new Error(msg);
            }

            const data = await response.json();

            setEmployees(data.content);
            setTotalPages(data.page.totalPages);
            setPage(page); // keep frontend 1-based

            setLoading(true);
            setError(null);
            setHasSearched(true);

        } catch (err) {
            setError(err.message);
            setEmployees([]);
            setHasSearched(false);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <div className="form-container">
                <div className="form-group">
                    <label>Enter Department Number:</label>
                    <div className="form-group">
                        <DepartmentSelect
                            name={"deptNo"}
                            value={deptNo}
                            onChange={(e) => setDeptNo(e.target.value)}
                            departments={departments}
                            disabled={loadingDepts}
                            label={"Department"}
                        />
                    </div>
                    <label>Enter Page Number:</label>
                    <input
                        type={"number"}
                        placeholder={"(optional)"}
                        min={1}
                        // value={page}
                        onChange={e => {
                            const value = Number(e.target.value);
                            if (value < 1) {
                                setError("Invalid page number.");
                            }
                            else {
                                setPage(value);
                                setError(null);
                            }
                        }}
                    />
                    <button disabled={!deptNo} onClick={() => fetchEmployees(page)}>
                        Search
                    </button>
                </div>
            </div>

            {error && <p style={{color: "red"}}>{error}</p>}

            {loading && <p>Loading...</p>}

            {hasSearched && (
                <>
                    <DepartmentEmployeesTable
                        departmentEmployees={employees}
                        disabled={loading}/>

                    <div>
                        <button
                            disabled={loading || page === 1}
                            onClick={() => fetchEmployees(page - 1)}
                        >
                            Previous
                        </button>

                        <span> Page {Number.isFinite(page) ? page : 1} of {totalPages} </span>

                        <button
                            disabled={loading || page === totalPages || totalPages === 0}
                            onClick={() => fetchEmployees(page + 1)}
                        >
                            Next
                        </button>
                    </div>
                </>
            )}

        </div>
    );
}

export default DepartmentEmployees;
