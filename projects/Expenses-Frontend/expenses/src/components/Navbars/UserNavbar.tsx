import { Link } from "react-router-dom";
import { useAuth } from "../security/Auth";
import { Modal,Button } from "react-bootstrap";
import "./UserStyle.css"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
function UserNavbar() {
  const auth = useAuth();
  const navigate = useNavigate()
  const isAuthenticated = auth.isAuthenticated;
  const [showModal,setShowModal] = useState(false)
  const handleCancel = ()=>{

  }
  return (
    <header className="border-bottom shadow-sm bg-white">
       <Modal show={showModal} onHide={handleCancel} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirm Logout</Modal.Title>
        </Modal.Header>
        <Modal.Body className="text-center">
          Are you sure you want to logout?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancel}>
            Cancel
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              navigate('/logout')
              setShowModal(false);
            }}
          >
            Logout
          </Button>
        </Modal.Footer>
      </Modal>

      <div className="container">
        <nav className="navbar navbar-expand-lg navbar-light">
          <Link className="navbar-brand fs-3 fw-bold text-primary" to="/">
            <i className="bi bi-wallet2 me-2"></i> Expense Tracker
          </Link>

          {/* Toggle Button for Mobile View */}
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav me-auto">
              {isAuthenticated && (
                <>
                  <li className="nav-item">
                    <Link className="nav-link fs-5 text-dark" to="/">
                      <i className="bi bi-house-door me-1"></i> Home
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link fs-5 text-dark" to="/Category">
                      <i className="bi bi-tags me-1"></i> Categories
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link
                      className="nav-link fs-5 text-dark"
                      to="/AddExpenses"
                      state={{ add: true, expenseId: -1 }}
                    >
                      <i className="bi bi-plus-circle me-1"></i> Add Expense
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link fs-5 text-dark" to="/ManageExpenses">
                      <i className="bi bi-list-check me-1"></i> Manage Expenses
                    </Link>
                  </li>
                </>
              )}
            </ul>

            {/* Auth Buttons Aligned Properly */}
            <ul className="navbar-nav ms-auto">
              {!isAuthenticated && (
                <li className="nav-item">
                  <Link className="nav-link fs-5 btn btn-primary px-3 text-white" to="/login">
                    <i className="bi bi-box-arrow-in-right me-1"></i> Login
                  </Link>
                </li>
              )}
              {isAuthenticated && (
                <>
                  <li className="nav-item">
                    <Link className="nav-link fs-5 text-dark" to="/profile">
                      <i className="bi bi-person-circle me-1"></i> Profile
                    </Link>
                  </li>
                  <li className="nav-item ms-3">
                    <a className="nav-link fs-5 btn btn-danger px-3 text-white" onClick={
                      ()=>setShowModal(true)}>
                      <i className="bi bi-box-arrow-right me-1"></i> Logout
                    </a>
                  </li>
                </>
              )}
            </ul>
          </div>
        </nav>
      </div>
    </header>
  );
}

export default UserNavbar;

