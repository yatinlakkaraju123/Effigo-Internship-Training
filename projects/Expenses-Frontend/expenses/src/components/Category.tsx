import { useEffect, useState } from "react";
import UserNavbar from "./Navbars/UserNavbar";
import { deleteCategory, retrieveCategoriesByUsers } from "./api/CategoryService";
import { CategoryInterface } from "../Interfaces";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./security/Auth";
import { Modal, Button, Card, Container, Row, Col } from "react-bootstrap";
import { FaEdit, FaTrashAlt, FaPlus } from "react-icons/fa";
import "./Category.css"
function Category() {
  const auth = useAuth();
  const navigate = useNavigate();
  const [categories, setCategories] = useState<CategoryInterface[]>([]);
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [updateId, setUpdateId] = useState(-1);
  const [deleteId, setDeleteId] = useState(-1);

  const handleCancelDelete = () => {
    setShowDeleteModal(false);
    setDeleteId(-1);
  };

  const handleUpdateCancel = () => {
    setShowUpdateModal(false);
    setUpdateId(-1);
  };

  const fetchAllCategories = async () => {
    const response = await retrieveCategoriesByUsers(auth.userId);
    setCategories(response.data);
  };

  useEffect(() => {
    fetchAllCategories();
  }, []);

  const update = async (id: number) => {
    navigate(`/category/${id}`);
  };

  const Delete = async (id: number) => {
    await deleteCategory(id);
    fetchAllCategories();
  };

  const Add = async () => {
    navigate("/category/-1");
  };

  return (
    <div>
      <UserNavbar />

      {/* Delete Confirmation Modal */}
      <Modal show={showDeleteModal} onHide={handleCancelDelete} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirm Deletion</Modal.Title>
        </Modal.Header>
        <Modal.Body className="text-center">
          Are you sure you want to delete this category?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancelDelete}>
            Cancel
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              Delete(deleteId);
              setShowDeleteModal(false);
            }}
          >
            Delete
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Update Confirmation Modal */}
      <Modal show={showUpdateModal} onHide={handleUpdateCancel} centered>
        <Modal.Header closeButton>
          <Modal.Title>Confirm Update</Modal.Title>
        </Modal.Header>
        <Modal.Body className="text-center">
          Do you want to update this category?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleUpdateCancel}>
            Cancel
          </Button>
          <Button variant="warning" onClick={() => update(updateId)}>
            Update
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Categories List */}
      <Container className="mt-4">
        <h2 className="text-center mb-4">Manage Categories</h2>
        <Row className="justify-content-center">
          {categories.map((item: CategoryInterface, index) => (
            <Col md={4} key={item.categoryId} className="mb-3">
              <Card className="shadow-sm">
                <Card.Body className="text-center">
                  <Card.Title>{item.name}</Card.Title>
                  <div className="d-flex justify-content-between">
                    <Button
                      variant="warning"
                      className="btn-sm"
                      onClick={() => {
                        setUpdateId(item.categoryId);
                        setShowUpdateModal(true);
                      }}
                    >
                      <FaEdit /> Edit
                    </Button>
                    <Button
                      variant="danger"
                      className="btn-sm"
                      onClick={() => {
                        setDeleteId(item.categoryId);
                        setShowDeleteModal(true);
                      }}
                    >
                      <FaTrashAlt /> Delete
                    </Button>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>

        {/* Add Category Button */}
        <div className="text-center mt-4">
          <Button variant="success" onClick={Add}>
            <FaPlus /> Add Category
          </Button>
        </div>
      </Container>
    </div>
  );
}

export default Category;
