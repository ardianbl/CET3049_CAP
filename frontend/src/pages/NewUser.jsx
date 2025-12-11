import { useOutletContext  } from "react-router";

const NewUser = () => {
  const [user, setUser] = useOutletContext()

  return (
    <div>
      <h2>Welcome {user}, enjoy your stay!</h2>
    </div>
  );
};

export default NewUser;
