type AdminViewProps = {
    navigateToLogin: () => void;
  };
  
  export default function AdminView({ navigateToLogin }: AdminViewProps) {
    return (
      <div>
        <h1>Admin Dashboard</h1>
        <button onClick={() => alert("List of users")}>View Users</button>
        <button onClick={() => alert("Delete user functionality")}>Delete User</button>
        <button onClick={() => alert("Clear password functionality")}>Clear Password</button>
        <button onClick={navigateToLogin}>Logout</button>
      </div>
    );
  }
  