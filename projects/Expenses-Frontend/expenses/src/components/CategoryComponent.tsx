import { useEffect, useState } from "react";
import UserNavbar from "./Navbars/UserNavbar";
import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "./security/Auth";
import { addCategory, retrieveCategoryById, updateCategory } from "./api/CategoryService";
import { Card, Spinner, Alert } from "react-bootstrap";

function CategoryComponent() {
  const auth = useAuth();
  const [name, setName] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const { id } = useParams();
  const itemId = Number(id);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCategory = async () => {
      if (itemId !== -1) {
        setLoading(true);
        try {
          const response = await retrieveCategoryById(itemId);
          setName(response.data.name);
        } catch (error) {
          setError("Failed to fetch category data.");
          console.error(error);
        } finally {
          setLoading(false);
        }
      }
    };
    fetchCategory();
  }, [itemId]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      if (itemId !== -1) {
        await updateCategory(itemId, name);
      } else {
        await addCategory(name, auth.userId);
      }
      navigate("/Category");
    } catch (error) {
      setError("An error occurred while saving the category.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <UserNavbar />
      <div className="container mt-4">
        <Card className="shadow p-4">
          <h3 className="mb-3 text-center">
            {itemId !== -1 ? "Update Category" : "Add New Category"}
          </h3>

          {error && <Alert variant="danger">{error}</Alert>}

          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label fw-bold">
                Category Name
              </label>
              <input
                type="text"
                className="form-control"
                value={name}
                onChange={(e) => setName(e.target.value)}
                id="name"
                placeholder="Enter category name"
                required
              />
            </div>

            <button
              type="submit"
              className="btn btn-primary w-100"
              disabled={!name || loading}
            >
              {loading ? (
                <>
                  <Spinner animation="border" size="sm" className="me-2" />
                  Saving...
                </>
              ) : (
                "Submit"
              )}
            </button>
          </form>
        </Card>
      </div>
    </div>
  );
}

export default CategoryComponent;
