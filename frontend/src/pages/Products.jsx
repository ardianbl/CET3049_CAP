import { Link, Outlet } from "react-router";

const Products = () => {
  return (
    <section>
      <h1>The Products Page</h1>
      <ul>
        {/* Initial */}
        {/* <li>A Book</li>
        <li>A Carpet</li>
        <li>An Apple iPad</li> */}

        {/* Using relative links where the parent link has the path pattern
         /products */}
        <li><Link to="p1">A Book</Link></li>
        <li><Link to="p2">A Carpet</Link></li>
        <li><Link to="p3">An Apple iPad</Link></li>
      </ul>
      <Outlet />
    </section>
  );
};

export default Products;
