import { useEffect, useState } from "react";
import { deleteExpense, retrieveExpensesByUser } from "./api/ExpenseService";
import UserNavbar from "./Navbars/UserNavbar";
import { ExpenseCompleteInterface } from "../Interfaces";
import { format } from "date-fns";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/Auth";
import { Modal,Button } from "react-bootstrap";
function ManageExpenses() {
  const [expenses, setExpenses] = useState<ExpenseCompleteInterface[]>([]);
  const [search, setSearch] = useState("");
  const [showDeleteModal,setShowDeleteModal] = useState(false)
  const [deleteId,setDeleteId] = useState(-1)
  const navigate = useNavigate();
  const auth = useAuth();

  useEffect(() => {
    fetchAllExpenses();
  }, []);

  const fetchAllExpenses = async () => {
    try {
      const response = await retrieveExpensesByUser(auth.userId);
      setExpenses(response.data);
    } catch (error) {
      console.error("Error fetching expenses:", error);
    }
  };

  const filteredExpenses = expenses.filter(expense =>
    expense.name.toLowerCase().includes(search.toLowerCase())
  );
  const handleCancelDelete = ()=>{
    setShowDeleteModal(false)
  }
  return (
    <div className="container">
      <UserNavbar />
      <Modal show={showDeleteModal} onHide={handleCancelDelete} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirm Deletion</Modal.Title>
        </Modal.Header>
        <Modal.Body className="text-center">
          Are you sure you want to delete this expense?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancelDelete}>
            Cancel
          </Button>
          <Button
            variant="danger"
            onClick={async () => {
              try {
                await deleteExpense(deleteId)
                fetchAllExpenses()

              } catch (error) {
                
              }
              setShowDeleteModal(false);
            }}
          >
            Delete
          </Button>
        </Modal.Footer>
      </Modal>

      <div className="card shadow p-4">
        <h2 className="text-center mb-4">Manage Expenses</h2>
        <input type="text" className="form-control mb-3" placeholder="Search by name..." value={search} onChange={(e) => setSearch(e.target.value)} />
        <table className="table table-striped">
          <thead className="table-dark">
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Price</th>
              <th>Description</th>
              <th>Date</th>
              <th>Category</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredExpenses.length > 0 ? (
              filteredExpenses.map((expense, index) => (
                <tr key={index}>
                  <td>{index + 1}</td>
                  <td>{expense.name}</td>
                  <td>{expense.price}</td>
                  <td>{expense.description}</td>
                  <td>{format(expense.date, "dd-MM-yyyy")}</td>
                  <td>{expense.category.name}</td>
                  <td>
                    <button className="btn btn-warning me-2" onClick={() => navigate("/AddExpenses", { state: { add: false, expenseId: expense.expenseId } })}>Edit</button>
                    <button className="btn btn-danger" onClick={() => {
                        setDeleteId(expense.expenseId)
                        setShowDeleteModal(true)
                    }}>Delete</button>
                  </td>
                </tr>
              ))
            ) : <tr><td colSpan={7} className="text-center">No expenses found</td></tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ManageExpenses;

