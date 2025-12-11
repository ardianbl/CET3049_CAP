import { useParams } from 'react-router';

const ProductDetail = (props) => {
  const params = useParams();
  console.log(params.productId);

  return (
    <section>
      <h1>Product Detail</h1>
      {params.productId == null ? <p>{props.productId}</p> : <p>{params.productId}</p> }
    </section>
  );
};

export default ProductDetail;
