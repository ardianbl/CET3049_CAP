import classes from "./MainHeader.module.css";

const MainHeader = () => {
  return (
    <header className={classes.header}>
      <nav>
        <ul>
          <li>
            <a href="/departments">Departments</a>
            {/* <a href="/welcome">Welcome</a> */}
            {/* <Link to="/welcome">Welcome</Link> */}
            {/* <NavLink className={(navData) => navData.isActive ? classes.active : "" } to="/welcome">Welcome</NavLink> */}
          </li>
          <li>
            <a href="/employee">Search Employee by Employee Number</a>
            {/* <a href="/welcome">Welcome</a> */}
            {/* <Link to="/welcome">Welcome</Link> */}
            {/* <NavLink className={(navData) => navData.isActive ? classes.active : "" } to="/welcome">Welcome</NavLink> */}
          </li>
          <li>
            <a href="/departmentEmployees">
              Search Employees by Department Number
            </a>
            {/* <a href="/welcome">Welcome</a> */}
            {/* <Link to="/welcome">Welcome</Link> */}
            {/* <NavLink className={(navData) => navData.isActive ? classes.active : "" } to="/welcome">Welcome</NavLink> */}
          </li>
          <li>
            <a href="/promoteEmployee">Promote an Employee</a>
            {/* <a href="/welcome">Welcome</a> */}
            {/* <Link to="/welcome">Welcome</Link> */}
            {/* <NavLink className={(navData) => navData.isActive ? classes.active : "" } to="/welcome">Welcome</NavLink> */}
          </li>
          <li>
            <a href="/welcome">Welcome</a>
            {/* <a href="/welcome">Welcome</a> */}
            {/* <Link to="/welcome">Welcome</Link> */}
            {/* <NavLink className={(navData) => navData.isActive ? classes.active : "" } to="/welcome">Welcome</NavLink> */}
          </li>
          <li>
            <a href="/products">Product</a>
            {/* <Link to="/products">Product</Link> */}
            {/* <NavLink className={(navData) => navData.isActive ? classes.active : "" } to="/products">Products</NavLink> */}
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default MainHeader;
