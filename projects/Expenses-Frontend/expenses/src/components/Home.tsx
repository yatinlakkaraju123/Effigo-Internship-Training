import UserNavbar from "./Navbars/UserNavbar";
import { useAuth } from "../components/security/Auth";
import { ToastContainer } from "react-toastify";

function Home() {
  const auth = useAuth();

  return (
    <div
      className="d-flex flex-column min-vh-100"
      style={{
        background: "linear-gradient(to right, #dfe9f3, #f3f3f3)", // Light pastel gradient
        backgroundSize: "cover",
      }}
    >
      <UserNavbar />
      <ToastContainer />
      <div className="container d-flex flex-column align-items-center justify-content-center flex-grow-1">
        <div
          className="card shadow-lg p-4 text-center"
          style={{
            maxWidth: "400px",
            background: "rgba(255, 255, 255, 0.85)", // Softer white transparency
            backdropFilter: "blur(10px)",
            borderRadius: "15px",
            border: "1px solid rgba(255, 255, 255, 0.3)",
          }}
        >
          <h3 className="card-title text-primary">Welcome, {auth.username}!</h3>
          <p className="text-muted">We're glad to have you here.</p>
        </div>
      </div>
    </div>
  );
}

export default Home;


