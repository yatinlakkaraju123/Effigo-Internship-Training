import { useEffect, useState } from "react";
import { format } from "date-fns";
import UserNavbar from "./Navbars/UserNavbar";
import { retrieveAllCategories, retrieveCategoriesByUsers } from "./api/CategoryService";
import { CategoryInterface, ExpenseInterface, ExpenseLink } from "../Interfaces";
import { addExpense, retrieveExpenseById, updateExpense } from "./api/ExpenseService";
import { useAuth } from "./security/Auth";
import { useLocation, useNavigate } from "react-router-dom";
import { Modal,Button } from "react-bootstrap";
function AddExpenses() {
  const location = useLocation();
  const navigate = useNavigate();
  const { add, expenseId } = location.state as ExpenseLink;
  const auth = useAuth();

  const [categories, setCategories] = useState<CategoryInterface[]>([]);
  const [categoryId, setCategoryId] = useState(0);
  const [loading,setLoading] = useState(false)
  const [showModal,setShowModal] = useState(false)
  const [expense, setExpense] = useState<ExpenseInterface>({
    name: "",
    price: 0,
    description: "",
    date: new Date(),
  });

  useEffect(() => {
    fetchCategories();
    setForm();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await retrieveCategoriesByUsers(auth.userId);
      setCategories(response.data);
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  };

  const setForm = async () => {
    if (!add) {
      try {
        const response = await retrieveExpenseById(expenseId);
        setExpense({
          name: response.data.name,
          price: response.data.price,
          description: response.data.description,
          date: new Date(response.data.date),
        });
        setCategoryId(response.data.category.id);
      } catch (error) {
        console.error("Error fetching expense details:", error);
      }
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setExpense((prevExpense) => ({
      ...prevExpense,
      [name]: name === "date" ? new Date(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setShowModal(true)

    //const formattedExpense = { ...expense, date: format(expense.date, "yyyy-MM-dd") };

  
  };
  const handleCancel = ()=>{
      setShowModal(false)
  }
  return (
    <div>
      <UserNavbar />
      <Modal show={showModal} onHide={handleCancel} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirm</Modal.Title>
        </Modal.Header>
        <Modal.Body className="text-center">
          Are you sure you want to {add ? "Add" : "Update"} this expense?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancel}>
            Cancel
          </Button>
          <Button
            variant="success"
            onClick={async() => {
              setLoading(true)
              try {
                if (add) {
                  await addExpense(auth.userId, categoryId,expense);
                } else {
                  await updateExpense(expenseId, expense);
                }
                navigate("/ManageExpenses");
                
              } catch (error) {
                console.error("Error submitting expense:", error);
              } finally{
                setLoading(false)
              }
            }}
          >
            Yes
          </Button>
        </Modal.Footer>
      </Modal>
      <div className="container mt-4">
        <div className="card shadow-lg p-4">
          <h2 className="text-center mb-4">{add ? "Add Expense" : "Edit Expense"}</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label fw-bold">
                Expense Name
              </label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={expense.name}
                onChange={handleChange}
                placeholder="Enter expense name"
                required
              />
            </div>

            <div className="mb-3">
              <label htmlFor="price" className="form-label fw-bold">
                Price
              </label>
              <input
                type="number"
                className="form-control"
                id="price"
                name="price"
                value={expense.price}
                onChange={handleChange}
                placeholder="Enter amount"
                required
              />
            </div>

            <div className="mb-3">
              <label htmlFor="description" className="form-label fw-bold">
                Description
              </label>
              <textarea
                className="form-control"
                id="description"
                name="description"
                value={expense.description}
                onChange={handleChange}
                placeholder="Enter a brief description"
                rows={3}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="date" className="form-label fw-bold">
                Date
              </label>
              <input
                type="date"
                className="form-control"
                id="date"
                name="date"
                value={format(expense.date, "yyyy-MM-dd")}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label htmlFor="category" className="form-label fw-bold">
                Category
              </label>
              <select
                className="form-select"
                id="category"
                name="category"
                value={categoryId || ""}
                onChange={(e) => setCategoryId(Number(e.target.value))}
                required
              >
                <option value="" disabled>
                  Select a category
                </option>
                {categories.map((item) => (
                  <option key={item.categoryId} value={item.categoryId}>
                    {item.name}
                  </option>
                ))}
              </select>
            </div>

            <div className="text-center">
              <button type="submit" className="btn btn-success px-4 me-2" 
              disabled={ expense.name=="" 
                || expense.price==0 
                || expense.description == ""
                || expense.date == new Date()
                || categoryId ==0
                || loading}
              
              >
                {add ? "Add Expense" : "Update Expense"}
              </button>
              <button type="button" className="btn btn-secondary px-4" onClick={() => navigate("/ManageExpenses")}>
                Cancel
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddExpenses;
