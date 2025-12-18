import React, {useEffect, useState} from "react";
import "./Style.css";
import PromotionResultTable from "./PromotionResultTable.jsx";
import DepartmentSelect from "../components/DepartmentSelect.jsx";

function PromoteEmployee() {
    const [form, setForm] = useState({
        empNo: "",
        newTitle: "",
        newDeptNo: "",
        newSalary: "",
        startDate: "",
        toManager: false
    });

    const [result, setResult] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const [departments, setDepartments] = useState([]);
    const [loadingDepts, setLoadingDepts] = useState(true);


    useEffect(() => {
        fetchDepartments();
    }, []);

    const handleChange = (e) => {
        const {name, value, type, checked} = e.target;

        setForm(prev => ({
            ...prev,
            [name]: type === "checkbox" ? checked : value
        }));
    };

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

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:8080/api/promote", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    ...form,
                    empNo: Number(form.empNo),
                    newSalary: Number(form.newSalary)
                })
            });

            if (!response.ok) {
                const msg = await response.text();
                throw new Error(msg);
            }

            const data = await response.json();
            setResult(data);   // <-- List<PromotionDTO>

        } catch (err) {
            setError(err.message);
            setResult([]);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <form className="form-container" onSubmit={handleSubmit}>

                <div className="form-group">
                    <label htmlFor="empNo">Employee Number</label>
                    <input
                        id="empNo"
                        name="empNo"
                        type="number"
                        placeholder="e.g. 10001"
                        value={form.empNo}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="newTitle">New Title</label>
                    <input
                        id="newTitle"
                        name="newTitle"
                        type="text"
                        maxLength={50}
                        placeholder="e.g. Manager"
                        value={form.newTitle}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="newDeptNo">New Department (optional)</label>

                    <DepartmentSelect
                        name={"newDeptNo"}
                        value={form.newDeptNo}
                        onChange={handleChange}
                        departments={departments}
                        disabled={loadingDepts}
                        label={"New Department"}
                    />
                </div>


                <div className="form-group">
                    <label htmlFor="newSalary">New Salary</label>
                    <input
                        id="newSalary"
                        name="newSalary"
                        type="number"
                        step="0.01"
                        placeholder="e.g. 95000.00"
                        value={form.newSalary}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="startDate">Effective Start Date</label>
                    <input
                        id="startDate"
                        name="startDate"
                        type="date"
                        value={form.startDate}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="checkbox-group">
                    <label>
                        <input
                            type="checkbox"
                            name="toManager"
                            checked={form.toManager}
                            onChange={handleChange}
                        />
                        Promote to Manager
                    </label>
                </div>

                <button
                    className={"submit-button"}
                    type="submit"
                    disabled={loading}
                >
                    {loading ? "Processing..." : "Submit Promotion"}
                </button>

            </form>


            {error && <p style={{color: "red"}}>{error}</p>}

            {result.length === 2 && (
                <>
                    <h3>Promotion Request Successful</h3>
                    <h3>Employee #{result[0].empNo}</h3>

                    <PromotionResultTable
                        before={result[0]}
                        after={result[1]}
                    />
                </>
            )}

        </div>
    );
}

export default PromoteEmployee;
