import { Routes, Route, Navigate, Outlet } from "react-router";

import Departments from "./pages/Departments";
import Employee from "./pages/Employee";
import Welcome from "./pages/Welcome";
import Home from "./pages/Home";
import NewUser from "./pages/NewUser";
import Products from "./pages/Products";
import MainHeader from "./components/MainHeader";
import NoMatch from "./pages/NoMatch";
import DepartmentEmployees from "./pages/DepartmentEmployees";
import PromoteEmployee from "./pages/PromoteEmployee";

function App() {
  return (
    <div>
      <header>
        <MainHeader />
      </header>
      <main>
        <Routes>
          {/* Initial route ---------------------*/}
          <Route path="/" element={<Home />} />
          <Route path="departments" element={<Departments />} />
          <Route path="employee" element={<Employee />} />
          <Route path="departmentEmployees" element={<DepartmentEmployees />} />
          <Route path="promoteEmployee" element={<PromoteEmployee />} />
          <Route path="welcome" element={<Welcome />} />
          <Route path="products" element={<Products />} />

          {/* Automatically redirecting the root path to the Welcome page */}
          <Route path="/" element={<Navigate replace to="welcome" />} />

          <Route path="welcome" element={<Welcome />}>
            <Route path="new-user" element={<NewUser />} />
          </Route>

          {/* Products details will be shown under the products content */}
          {/* <Route path="products" element={<Products />} >
            <Route index element={<ProductDetail productId="p1" />} />
            <Route path=":productId" element={<ProductDetail />} />
          </Route> */}

          {/* Products details will be shown on a different page from the products content */}
          {/* <Route path="products/:productId" element={<ProductDetail />} /> */}

          {/* For invalid URLs */}
          <Route path="*" element={<NoMatch />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
