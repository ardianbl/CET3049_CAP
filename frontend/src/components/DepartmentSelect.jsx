function DepartmentSelect(
    {
        name,
        value,
        onChange,
        departments,
        label = "Department",
        placeholder = "— Select department —",
        disabled = false,
        required = false
    }) {

    return (
        <div className="form-group">
            <select
                name={name}
                value={value ?? ""}
                onChange={onChange}
                disabled={disabled}
                required={required}
            >
                <option value="">{placeholder}</option>

                {departments.map((dept) => (
                    <option key={dept.deptNo} value={dept.deptNo}>
                        {dept.deptNo} — {dept.deptName}
                    </option>
                ))}
            </select>
        </div>
    );
}

export default DepartmentSelect;
