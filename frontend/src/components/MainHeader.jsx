import {Snowfall} from "react-snowfall";
import {FaSnowflake} from "react-icons/fa"
import classes from "./MainHeader.module.css";
import {useState} from "react";

const MainHeader = () => {
    const [snowing, setSnowing] = useState(true);

    return (
        <header className={classes.header}>
            {/* Snowfall effect */}
            {snowing && <Snowfall/>}

            {/* Santa Claus image at bottom-right */}
            <img
                src="/7901609.jpg"
                alt="Santa Claus"
                className={classes.santa}
            />

            {/* Snow toggle button */}
            <button
                onClick={() => setSnowing(!snowing)}
                className={classes.snowButton}
                title={snowing ? "" : ""}
            >
                <FaSnowflake style={{marginRight: "6px"}}/>
                {snowing ? "" : ""}
            </button>

            <nav>
                <ul>
                    <li><a href="/departments">Departments</a></li>
                    <li><a href="/employees">Search Employee by Employee Number</a></li>
                    <li><a href="/departmentEmployees">Search Employees by Department Number</a></li>
                    <li><a href="/promoteEmployee">Promote an Employee</a></li>
                </ul>
            </nav>
        </header>
    );
};

export default MainHeader;
