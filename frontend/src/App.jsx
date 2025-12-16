import {Routes, Route, Navigate, Outlet} from "react-router";

import Departments from "./pages/Departments";
import Employees from "./pages/Employees.jsx";
import Home from "./pages/Home";
import MainHeader from "./components/MainHeader";
import NoMatch from "./pages/NoMatch";
import DepartmentEmployees from "./pages/DepartmentEmployees";
import PromoteEmployee from "./pages/PromoteEmployee";

function App() {
    return (
        <div>
            <header>
                <MainHeader/>
            </header>
            <main>
                <Routes>
                    {/* Initial route ---------------------*/}
                    <Route path="/" element={<Home/>}/>
                    <Route path="departments" element={<Departments/>}/>
                    <Route path="employees" element={<Employees/>}/>
                    <Route path="departmentEmployees" element={<DepartmentEmployees/>}/>
                    <Route path="promoteEmployee" element={<PromoteEmployee/>}/>

                    {/*/!* Automatically redirecting the root path to the Welcome page *!/*/}
                    {/*<Route path="/" element={<Navigate replace to="welcome" />} />*/}

                    {/*<Route path="welcome" element={<Welcome />}>*/}
                    {/*  <Route path="new-user" element={<NewUser />} />*/}
                    {/*</Route>*/}

                    {/* For invalid URLs */}
                    <Route path="*" element={<NoMatch/>}/>
                </Routes>
            </main>
        </div>
    );
}

export default App;
